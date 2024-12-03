/*
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.logic;

import io.vizend.accent.domain.type.NameValueList;
import io.vizend.accent.util.entity.Entities;
import io.vizend.prologue.janitor.proxy.EventProxy;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.entity.Ehebmbhvr;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.entity.sdo.EhebmbhvrCdo;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.event.EhebmbhvrEvent;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.store.EhebmbhvrOptionStore;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.store.EhebmbhvrStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class EhebmbhvrLogic {
    /* Gen by Vizend Studio v6.0.0 */
    private final EhebmbhvrStore ehebmbhvrStore;
    private final EhebmbhvrOptionStore ehebmbhvrOptionStore;
    private final EventProxy eventProxy;

    public EhebmbhvrLogic(EhebmbhvrStore ehebmbhvrStore, EhebmbhvrOptionStore ehebmbhvrOptionStore, EventProxy eventProxy) {
        /* Gen by Vizend Studio v6.0.0 */
        this.ehebmbhvrStore = ehebmbhvrStore;
        this.ehebmbhvrOptionStore = ehebmbhvrOptionStore;
        this.eventProxy = eventProxy;
    }

    public String registerEhebmbhvr(EhebmbhvrCdo ehebmbhvrCdo) {
        /* Gen by Vizend Studio v6.0.0 */
        Ehebmbhvr ehebmbhvr = new Ehebmbhvr(ehebmbhvrCdo);
        if (ehebmbhvrCdo.hasAdditionalAttributes()) {
            ehebmbhvr.modify(ehebmbhvrCdo.getAdditionalAttributes());
        }
        if (ehebmbhvrStore.exists(ehebmbhvr.getId())) {
            throw new IllegalArgumentException("ehebmbhvr already exists. " + ehebmbhvr.getId());
        }
        ehebmbhvrStore.create(ehebmbhvr);
        EhebmbhvrEvent ehebmbhvrEvent = EhebmbhvrEvent.newEhebmbhvrRegisteredEvent(ehebmbhvr, ehebmbhvr.getId());
        eventProxy.publishEvent(ehebmbhvrEvent);
        return ehebmbhvr.getId();
    }

    public List<String> registerEhebmbhvrs(List<EhebmbhvrCdo> ehebmbhvrCdos) {
        /* Gen by Vizend Studio v6.0.0 */
        return ehebmbhvrCdos.stream().map(this::registerEhebmbhvr).toList();
    }

    public Ehebmbhvr findEhebmbhvr(String ehebmbhvrId) {
        /* Gen by Vizend Studio v6.0.0 */
        Ehebmbhvr ehebmbhvr = ehebmbhvrStore.retrieve(ehebmbhvrId);
        if (ehebmbhvr == null) {
            throw new NoSuchElementException("Ehebmbhvr id: " + ehebmbhvrId);
        }
        return ehebmbhvr;
    }

    public void modifyEhebmbhvr(String ehebmbhvrId, NameValueList nameValues) {
        /* Gen by Vizend Studio v6.0.0 */
        Ehebmbhvr ehebmbhvr = findEhebmbhvr(ehebmbhvrId);
        ehebmbhvr.modify(nameValues);
        ehebmbhvrStore.update(ehebmbhvr);
        EhebmbhvrEvent ehebmbhvrEvent = EhebmbhvrEvent.newEhebmbhvrModifiedEvent(ehebmbhvrId, nameValues, ehebmbhvr);
        eventProxy.publishEvent(ehebmbhvrEvent);
    }

    public void modifyEhebmbhvr(Ehebmbhvr ehebmbhvr) {
        /* Gen by Vizend Studio v6.0.0 */
        Ehebmbhvr oldEhebmbhvr = findEhebmbhvr(ehebmbhvr.getId());
        NameValueList nameValues = Entities.getModifiedNameValues(oldEhebmbhvr, ehebmbhvr);
        if (nameValues.size() > 0) {
            modifyEhebmbhvr(ehebmbhvr.getId(), nameValues);
        }
    }

    public void removeEhebmbhvr(String ehebmbhvrId) {
        /* Gen by Vizend Studio v6.0.0 */
        Ehebmbhvr ehebmbhvr = findEhebmbhvr(ehebmbhvrId);
        ehebmbhvrStore.delete(ehebmbhvr);
        EhebmbhvrEvent ehebmbhvrEvent = EhebmbhvrEvent.newEhebmbhvrRemovedEvent(ehebmbhvr, ehebmbhvr.getId());
        eventProxy.publishEvent(ehebmbhvrEvent);
    }

    public boolean existsEhebmbhvr(String ehebmbhvrId) {
        /* Gen by Vizend Studio v6.0.0 */
        return ehebmbhvrStore.exists(ehebmbhvrId);
    }

    public void handleEventForProjection(EhebmbhvrEvent ehebmbhvrEvent) {
        /* Gen by Vizend Studio v6.0.0 */
        switch (ehebmbhvrEvent.getDataEventType()) {
            case Registered:
                ehebmbhvrStore.create(ehebmbhvrEvent.getEhebmbhvr());
                break;
            case Modified:
                Ehebmbhvr ehebmbhvr = ehebmbhvrStore.retrieve(ehebmbhvrEvent.getEhebmbhvrId());
                ehebmbhvr.modify(ehebmbhvrEvent.getNameValues());
                ehebmbhvrStore.update(ehebmbhvr);
                break;
            case Removed:
                ehebmbhvrStore.delete(ehebmbhvrEvent.getEhebmbhvrId());
                break;
        }
    }

    public Ehebmbhvr findByCircumBhvrKnackSn(Long circumBhvrKnackSn) {
        /* Gen by Vizend Studio v6.0.0 */
        return ehebmbhvrOptionStore.retrieveByCircumBhvrKnackSn(circumBhvrKnackSn);
    }

    public List<Ehebmbhvr> findByDscpTypCd(String dscpTypCd) {
        /* Gen by Vizend Studio v6.0.0 */
        return ehebmbhvrOptionStore.retrieveByDscpTypCd(dscpTypCd);
    }

    public List<Ehebmbhvr> findByScenCircumCdAndDscpTypCd(String scenCircumCd, String dscpTypCd) {
        /* Gen by Vizend Studio v6.0.0 */
        return ehebmbhvrOptionStore.retrieveByScenCircumCdAndDscpTypCd(scenCircumCd, dscpTypCd);
    }

    public List<Ehebmbhvr> findByCircumBhvrKnackSnAndScenCircumCdAndDscpTypCd(Long circumBhvrKnackSn, String scenCircumCd, String dscpTypCd) {
        /* Gen by Vizend Studio v6.0.0 */
        return ehebmbhvrOptionStore.retrieveByCircumBhvrKnackSnAndScenCircumCdAndDscpTypCd(circumBhvrKnackSn, scenCircumCd, dscpTypCd);
    }

    public Ehebmbhvr findTopByOrderByCircumBhvrKnackSnDesc() {
        /* Gen by Vizend Studio v6.0.0 */
        return ehebmbhvrOptionStore.retrieveTopByOrderByCircumBhvrKnackSnDesc();
    }
}
