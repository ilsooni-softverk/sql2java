/*
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.logic;

import io.vizend.accent.domain.type.NameValueList;
import io.vizend.accent.util.entity.Entities;
import io.vizend.prologue.janitor.proxy.EventProxy;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.entity.Ehebmcdsc;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.entity.sdo.EhebmcdscCdo;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.event.EhebmcdscEvent;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.store.EhebmcdscOptionStore;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.store.EhebmcdscStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class EhebmcdscLogic {
    /* Gen by Vizend Studio v6.0.0 */
    private final EhebmcdscStore ehebmcdscStore;
    private final EhebmcdscOptionStore ehebmcdscOptionStore;
    private final EventProxy eventProxy;

    public EhebmcdscLogic(EhebmcdscStore ehebmcdscStore, EhebmcdscOptionStore ehebmcdscOptionStore, EventProxy eventProxy) {
        /* Gen by Vizend Studio v6.0.0 */
        this.ehebmcdscStore = ehebmcdscStore;
        this.ehebmcdscOptionStore = ehebmcdscOptionStore;
        this.eventProxy = eventProxy;
    }

    public String registerEhebmcdsc(EhebmcdscCdo ehebmcdscCdo) {
        /* Gen by Vizend Studio v6.0.0 */
        Ehebmcdsc ehebmcdsc = new Ehebmcdsc(ehebmcdscCdo);
        if (ehebmcdscCdo.hasAdditionalAttributes()) {
            ehebmcdsc.modify(ehebmcdscCdo.getAdditionalAttributes());
        }
        if (ehebmcdscStore.exists(ehebmcdsc.getId())) {
            throw new IllegalArgumentException("ehebmcdsc already exists. " + ehebmcdsc.getId());
        }
        ehebmcdscStore.create(ehebmcdsc);
        EhebmcdscEvent ehebmcdscEvent = EhebmcdscEvent.newEhebmcdscRegisteredEvent(ehebmcdsc, ehebmcdsc.getId());
        eventProxy.publishEvent(ehebmcdscEvent);
        return ehebmcdsc.getId();
    }

    public List<String> registerEhebmcdscs(List<EhebmcdscCdo> ehebmcdscCdos) {
        /* Gen by Vizend Studio v6.0.0 */
        return ehebmcdscCdos.stream().map(this::registerEhebmcdsc).toList();
    }

    public Ehebmcdsc findEhebmcdsc(String ehebmcdscId) {
        /* Gen by Vizend Studio v6.0.0 */
        Ehebmcdsc ehebmcdsc = ehebmcdscStore.retrieve(ehebmcdscId);
        if (ehebmcdsc == null) {
            throw new NoSuchElementException("Ehebmcdsc id: " + ehebmcdscId);
        }
        return ehebmcdsc;
    }

    public void modifyEhebmcdsc(String ehebmcdscId, NameValueList nameValues) {
        /* Gen by Vizend Studio v6.0.0 */
        Ehebmcdsc ehebmcdsc = findEhebmcdsc(ehebmcdscId);
        ehebmcdsc.modify(nameValues);
        ehebmcdscStore.update(ehebmcdsc);
        EhebmcdscEvent ehebmcdscEvent = EhebmcdscEvent.newEhebmcdscModifiedEvent(ehebmcdscId, nameValues, ehebmcdsc);
        eventProxy.publishEvent(ehebmcdscEvent);
    }

    public void modifyEhebmcdsc(Ehebmcdsc ehebmcdsc) {
        /* Gen by Vizend Studio v6.0.0 */
        Ehebmcdsc oldEhebmcdsc = findEhebmcdsc(ehebmcdsc.getId());
        NameValueList nameValues = Entities.getModifiedNameValues(oldEhebmcdsc, ehebmcdsc);
        if (nameValues.size() > 0) {
            modifyEhebmcdsc(ehebmcdsc.getId(), nameValues);
        }
    }

    public void removeEhebmcdsc(String ehebmcdscId) {
        /* Gen by Vizend Studio v6.0.0 */
        Ehebmcdsc ehebmcdsc = findEhebmcdsc(ehebmcdscId);
        ehebmcdscStore.delete(ehebmcdsc);
        EhebmcdscEvent ehebmcdscEvent = EhebmcdscEvent.newEhebmcdscRemovedEvent(ehebmcdsc, ehebmcdsc.getId());
        eventProxy.publishEvent(ehebmcdscEvent);
    }

    public boolean existsEhebmcdsc(String ehebmcdscId) {
        /* Gen by Vizend Studio v6.0.0 */
        return ehebmcdscStore.exists(ehebmcdscId);
    }

    public void handleEventForProjection(EhebmcdscEvent ehebmcdscEvent) {
        /* Gen by Vizend Studio v6.0.0 */
        switch(ehebmcdscEvent.getDataEventType()) {
            case Registered:
                ehebmcdscStore.create(ehebmcdscEvent.getEhebmcdsc());
                break;
            case Modified:
                Ehebmcdsc ehebmcdsc = ehebmcdscStore.retrieve(ehebmcdscEvent.getEhebmcdscId());
                ehebmcdsc.modify(ehebmcdscEvent.getNameValues());
                ehebmcdscStore.update(ehebmcdsc);
                break;
            case Removed:
                ehebmcdscStore.delete(ehebmcdscEvent.getEhebmcdscId());
                break;
        }
    }

    public Ehebmcdsc findByScenCircumCdAndDscpTypCd(String scenCircumCd, String dscpTypCd) {
        /* Gen by Vizend Studio v6.0.0 */
        return ehebmcdscOptionStore.retrieveByScenCircumCdAndDscpTypCd(scenCircumCd, dscpTypCd);
    }

    public List<Ehebmcdsc> findByDscpTypCd(String dscpTypCd) {
        /* Gen by Vizend Studio v6.0.0 */
        return ehebmcdscOptionStore.retrieveByDscpTypCd(dscpTypCd);
    }

    public Ehebmcdsc findTopByDscpTypCdOrderByScenCircumCdDesc(String dscpTypCd) {
        /* Gen by Vizend Studio v6.0.0 */
        return ehebmcdscOptionStore.retrieveTopByDscpTypCdOrderByScenCircumCdDesc(dscpTypCd);
    }
}
