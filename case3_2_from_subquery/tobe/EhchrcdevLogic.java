/*
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.logic;

import io.vizend.accent.domain.type.NameValueList;
import io.vizend.accent.util.entity.Entities;
import io.vizend.prologue.janitor.proxy.EventProxy;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.entity.Ehchrcdev;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.entity.sdo.EhchrcdevCdo;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.event.EhchrcdevEvent;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.store.EhchrcdevOptionStore;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.store.EhchrcdevStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class EhchrcdevLogic {
    /* Gen by Vizend Studio v5.1.0 */
    private final EhchrcdevStore ehchrcdevStore;
    private final EhchrcdevOptionStore ehchrcdevOptionStore;
    private final EventProxy eventProxy;

    public EhchrcdevLogic(EhchrcdevStore ehchrcdevStore, EhchrcdevOptionStore ehchrcdevOptionStore, EventProxy eventProxy) {
        /* Gen by Vizend Studio v5.1.0 */
        this.ehchrcdevStore = ehchrcdevStore;
        this.ehchrcdevOptionStore = ehchrcdevOptionStore;
        this.eventProxy = eventProxy;
    }

    public String registerEhchrcdev(EhchrcdevCdo ehchrcdevCdo) {
        /* Gen by Vizend Studio v5.1.0 */
        Ehchrcdev ehchrcdev = new Ehchrcdev(ehchrcdevCdo);
        if (ehchrcdevCdo.hasAdditionalAttributes()) {
            ehchrcdev.modify(ehchrcdevCdo.getAdditionalAttributes());
        }
        if (ehchrcdevStore.exists(ehchrcdev.getId())) {
            throw new IllegalArgumentException("ehchrcdev already exists. " + ehchrcdev.getId());
        }
        ehchrcdevStore.create(ehchrcdev);
        EhchrcdevEvent ehchrcdevEvent = EhchrcdevEvent.newEhchrcdevRegisteredEvent(ehchrcdev, ehchrcdev.getId());
        eventProxy.publishEvent(ehchrcdevEvent);
        return ehchrcdev.getId();
    }

    public List<String> registerEhchrcdevs(List<EhchrcdevCdo> ehchrcdevCdos) {
        /* Gen by Vizend Studio v5.1.0 */
        return ehchrcdevCdos.stream().map(this::registerEhchrcdev).toList();
    }

    public Ehchrcdev findEhchrcdev(String ehchrcdevId) {
        /* Gen by Vizend Studio v5.1.0 */
        Ehchrcdev ehchrcdev = ehchrcdevStore.retrieve(ehchrcdevId);
        if (ehchrcdev == null) {
            throw new NoSuchElementException("Ehchrcdev id: " + ehchrcdevId);
        }
        return ehchrcdev;
    }

    public void modifyEhchrcdev(String ehchrcdevId, NameValueList nameValues) {
        /* Gen by Vizend Studio v5.1.0 */
        Ehchrcdev ehchrcdev = findEhchrcdev(ehchrcdevId);
        ehchrcdev.modify(nameValues);
        ehchrcdevStore.update(ehchrcdev);
        EhchrcdevEvent ehchrcdevEvent = EhchrcdevEvent.newEhchrcdevModifiedEvent(ehchrcdevId, nameValues, ehchrcdev);
        eventProxy.publishEvent(ehchrcdevEvent);
    }

    public void modifyEhchrcdev(Ehchrcdev ehchrcdev) {
        /* Gen by Vizend Studio v5.1.0 */
        Ehchrcdev oldEhchrcdev = findEhchrcdev(ehchrcdev.getId());
        NameValueList nameValues = Entities.getModifiedNameValues(oldEhchrcdev, ehchrcdev);
        if (nameValues.size() > 0) {
            modifyEhchrcdev(ehchrcdev.getId(), nameValues);
        }
    }

    public void removeEhchrcdev(String ehchrcdevId) {
        /* Gen by Vizend Studio v5.1.0 */
        Ehchrcdev ehchrcdev = findEhchrcdev(ehchrcdevId);
        ehchrcdevStore.delete(ehchrcdev);
        EhchrcdevEvent ehchrcdevEvent = EhchrcdevEvent.newEhchrcdevRemovedEvent(ehchrcdev, ehchrcdev.getId());
        eventProxy.publishEvent(ehchrcdevEvent);
    }

    public boolean existsEhchrcdev(String ehchrcdevId) {
        /* Gen by Vizend Studio v5.1.0 */
        return ehchrcdevStore.exists(ehchrcdevId);
    }

    public void handleEventForProjection(EhchrcdevEvent ehchrcdevEvent) {
        /* Gen by Vizend Studio v5.1.0 */
        switch(ehchrcdevEvent.getDataEventType()) {
            case Registered:
                ehchrcdevStore.create(ehchrcdevEvent.getEhchrcdev());
                break;
            case Modified:
                Ehchrcdev ehchrcdev = ehchrcdevStore.retrieve(ehchrcdevEvent.getEhchrcdevId());
                ehchrcdev.modify(ehchrcdevEvent.getNameValues());
                ehchrcdevStore.update(ehchrcdev);
                break;
            case Removed:
                ehchrcdevStore.delete(ehchrcdevEvent.getEhchrcdevId());
                break;
        }
    }

    public List<Ehchrcdev> findByEhsPlanManageSnAndEhsDtlPlanManageSn(Long ehsPlanManageSn, Long ehsDtlPlanManageSn) {
        /* Gen by Vizend Studio v5.1.0 */
        return ehchrcdevOptionStore.retrieveByEhsPlanManageSnAndEhsDtlPlanManageSn(ehsPlanManageSn, ehsDtlPlanManageSn);
    }

    public Ehchrcdev findByEhsPlanManageSnAndEhsDtlPlanManageSnAndDscpEvalQuestSn(Long ehsPlanManageSn, Long ehsDtlPlanManageSn, Long dscpEvalQuestSn) {
        /* Gen by Vizend Studio v5.1.0 */
        return ehchrcdevOptionStore.retrieveByEhsPlanManageSnAndEhsDtlPlanManageSnAndDscpEvalQuestSn(ehsPlanManageSn, ehsDtlPlanManageSn, dscpEvalQuestSn);
    }

    public boolean existsByEhsPlanManageSnAndEhsDtlPlanManageSnAndDscpEvalQuestSn(Long ehsPlanManageSn, Long ehsDtlPlanManageSn, Long dscpEvalQuestSn) {
        /* Gen by Vizend Studio v5.1.0 */
        return ehchrcdevOptionStore.existsByEhsPlanManageSnAndEhsDtlPlanManageSnAndDscpEvalQuestSn(ehsPlanManageSn, ehsDtlPlanManageSn, dscpEvalQuestSn);
    }
}
