/*
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.logic;

import io.vizend.accent.domain.type.NameValueList;
import io.vizend.accent.util.entity.Entities;
import io.vizend.prologue.janitor.proxy.EventProxy;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.entity.Ehchmcdeq;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.entity.sdo.EhchmcdeqCdo;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.event.EhchmcdeqEvent;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.store.EhchmcdeqOptionStore;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.store.EhchmcdeqStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
public class EhchmcdeqLogic {
    /* Gen by Vizend Studio v6.0.0 */
    private final EhchmcdeqStore ehchmcdeqStore;
    private final EhchmcdeqOptionStore ehchmcdeqOptionStore;
    private final EventProxy eventProxy;

    public EhchmcdeqLogic(EhchmcdeqStore ehchmcdeqStore, EhchmcdeqOptionStore ehchmcdeqOptionStore, EventProxy eventProxy) {
        /* Gen by Vizend Studio v6.0.0 */
        this.ehchmcdeqStore = ehchmcdeqStore;
        this.ehchmcdeqOptionStore = ehchmcdeqOptionStore;
        this.eventProxy = eventProxy;
    }

    public String registerEhchmcdeq(EhchmcdeqCdo ehchmcdeqCdo) {
        /* Gen by Vizend Studio v6.0.0 */
        Ehchmcdeq ehchmcdeq = new Ehchmcdeq(ehchmcdeqCdo);
        if (ehchmcdeqCdo.hasAdditionalAttributes()) {
            ehchmcdeq.modify(ehchmcdeqCdo.getAdditionalAttributes());
        }
        if (ehchmcdeqStore.exists(ehchmcdeq.getId())) {
            throw new IllegalArgumentException("ehchmcdeq already exists. " + ehchmcdeq.getId());
        }
        ehchmcdeqStore.create(ehchmcdeq);
        EhchmcdeqEvent ehchmcdeqEvent = EhchmcdeqEvent.newEhchmcdeqRegisteredEvent(ehchmcdeq, ehchmcdeq.getId());
        eventProxy.publishEvent(ehchmcdeqEvent);
        return ehchmcdeq.getId();
    }

    public List<String> registerEhchmcdeqs(List<EhchmcdeqCdo> ehchmcdeqCdos) {
        /* Gen by Vizend Studio v6.0.0 */
        return ehchmcdeqCdos.stream().map(this::registerEhchmcdeq).collect(Collectors.toList());
    }

    public Ehchmcdeq findEhchmcdeq(String ehchmcdeqId) {
        /* Gen by Vizend Studio v6.0.0 */
        Ehchmcdeq ehchmcdeq = ehchmcdeqStore.retrieve(ehchmcdeqId);
        if (ehchmcdeq == null) {
            throw new NoSuchElementException("Ehchmcdeq id: " + ehchmcdeqId);
        }
        return ehchmcdeq;
    }

    public void modifyEhchmcdeq(String ehchmcdeqId, NameValueList nameValues) {
        /* Gen by Vizend Studio v6.0.0 */
        Ehchmcdeq ehchmcdeq = findEhchmcdeq(ehchmcdeqId);
        ehchmcdeq.modify(nameValues);
        ehchmcdeqStore.update(ehchmcdeq);
        EhchmcdeqEvent ehchmcdeqEvent = EhchmcdeqEvent.newEhchmcdeqModifiedEvent(ehchmcdeqId, nameValues, ehchmcdeq);
        eventProxy.publishEvent(ehchmcdeqEvent);
    }

    public void modifyEhchmcdeq(Ehchmcdeq ehchmcdeq) {
        /* Gen by Vizend Studio v6.0.0 */
        Ehchmcdeq oldEhchmcdeq = findEhchmcdeq(ehchmcdeq.getId());
        NameValueList nameValues = Entities.getModifiedNameValues(oldEhchmcdeq, ehchmcdeq);
        if (nameValues.size() > 0) {
            modifyEhchmcdeq(ehchmcdeq.getId(), nameValues);
        }
    }

    public void removeEhchmcdeq(String ehchmcdeqId) {
        /* Gen by Vizend Studio v6.0.0 */
        Ehchmcdeq ehchmcdeq = findEhchmcdeq(ehchmcdeqId);
        ehchmcdeqStore.delete(ehchmcdeq);
        EhchmcdeqEvent ehchmcdeqEvent = EhchmcdeqEvent.newEhchmcdeqRemovedEvent(ehchmcdeq, ehchmcdeq.getId());
        eventProxy.publishEvent(ehchmcdeqEvent);
    }

    public boolean existsEhchmcdeq(String ehchmcdeqId) {
        /* Gen by Vizend Studio v6.0.0 */
        return ehchmcdeqStore.exists(ehchmcdeqId);
    }

    public void handleEventForProjection(EhchmcdeqEvent ehchmcdeqEvent) {
        /* Gen by Vizend Studio v6.0.0 */
        switch(ehchmcdeqEvent.getDataEventType()) {
            case Registered:
                ehchmcdeqStore.create(ehchmcdeqEvent.getEhchmcdeq());
                break;
            case Modified:
                Ehchmcdeq ehchmcdeq = ehchmcdeqStore.retrieve(ehchmcdeqEvent.getEhchmcdeqId());
                ehchmcdeq.modify(ehchmcdeqEvent.getNameValues());
                ehchmcdeqStore.update(ehchmcdeq);
                break;
            case Removed:
                ehchmcdeqStore.delete(ehchmcdeqEvent.getEhchmcdeqId());
                break;
        }
    }

    public Ehchmcdeq findByDscpEvalQuestSn(Long dscpEvalQuestSn) {
        /* Gen by Vizend Studio v6.0.0 */
        return ehchmcdeqOptionStore.retrieveByDscpEvalQuestSn(dscpEvalQuestSn);
    }

    public List<Ehchmcdeq> findByDscpTypCd(String dscpTypCd) {
        /* Gen by Vizend Studio v6.0.0 */
        return ehchmcdeqOptionStore.retrieveByDscpTypCd(dscpTypCd);
    }

    public Ehchmcdeq findTopByOrderByDscpEvalQuestSnDesc() {
        /* Gen by Vizend Studio v6.0.0 */
        return ehchmcdeqOptionStore.retrieveTopByOrderByDscpEvalQuestSnDesc();
    }
}
