/*
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package kr.amc.amis.ehs.aggregate.plan.domain.logic;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import kr.amc.amis.ehs.aggregate.plan.store.EhebrnmntStore;
import kr.amc.amis.ehs.aggregate.plan.store.EhebrnmntOptionStore;
import io.vizend.prologue.janitor.proxy.EventProxy;
import kr.amc.amis.ehs.aggregate.plan.domain.entity.sdo.EhebrnmntCdo;
import kr.amc.amis.ehs.aggregate.plan.domain.event.EhebrnmntEvent;

import java.util.List;

import kr.amc.amis.ehs.aggregate.plan.domain.entity.Ehebrnmnt;
import java.util.NoSuchElementException;
import io.vizend.accent.domain.type.NameValueList;
import io.vizend.accent.util.entity.Entities;

@Service
@Transactional
public class EhebrnmntLogic {
    /* Gen by Vizend Studio v6.0.0 */
    private final EhebrnmntStore ehebrnmntStore;
    private final EhebrnmntOptionStore ehebrnmntOptionStore;
    private final EventProxy eventProxy;

    public EhebrnmntLogic(EhebrnmntStore ehebrnmntStore, EhebrnmntOptionStore ehebrnmntOptionStore, EventProxy eventProxy) {
        /* Gen by Vizend Studio v6.0.0 */
        this.ehebrnmntStore = ehebrnmntStore;
        this.ehebrnmntOptionStore = ehebrnmntOptionStore;
        this.eventProxy = eventProxy;
    }

    public String registerEhebrnmnt(EhebrnmntCdo ehebrnmntCdo) {
        /* Gen by Vizend Studio v6.0.0 */
        Ehebrnmnt ehebrnmnt = new Ehebrnmnt(ehebrnmntCdo);
        if (ehebrnmntCdo.hasAdditionalAttributes()) {
            ehebrnmnt.modify(ehebrnmntCdo.getAdditionalAttributes());
        }
        if (ehebrnmntStore.exists(ehebrnmnt.getId())) {
            throw new IllegalArgumentException("ehebrnmnt already exists. " + ehebrnmnt.getId());
        }
        ehebrnmntStore.create(ehebrnmnt);
        EhebrnmntEvent ehebrnmntEvent = EhebrnmntEvent.newEhebrnmntRegisteredEvent(ehebrnmnt, ehebrnmnt.getId());
        eventProxy.publishEvent(ehebrnmntEvent);
        return ehebrnmnt.getId();
    }

    public List<String> registerEhebrnmnts(List<EhebrnmntCdo> ehebrnmntCdos) {
        /* Gen by Vizend Studio v6.0.0 */
        return ehebrnmntCdos.stream().map(this::registerEhebrnmnt).toList();
    }

    public Ehebrnmnt findEhebrnmnt(String ehebrnmntId) {
        /* Gen by Vizend Studio v6.0.0 */
        Ehebrnmnt ehebrnmnt = ehebrnmntStore.retrieve(ehebrnmntId);
        if (ehebrnmnt == null) {
            throw new NoSuchElementException("Ehebrnmnt id: " + ehebrnmntId);
        }
        return ehebrnmnt;
    }

    public void modifyEhebrnmnt(String ehebrnmntId, NameValueList nameValues) {
        /* Gen by Vizend Studio v6.0.0 */
        Ehebrnmnt ehebrnmnt = findEhebrnmnt(ehebrnmntId);
        ehebrnmnt.modify(nameValues);
        ehebrnmntStore.update(ehebrnmnt);
        EhebrnmntEvent ehebrnmntEvent = EhebrnmntEvent.newEhebrnmntModifiedEvent(ehebrnmntId, nameValues, ehebrnmnt);
        eventProxy.publishEvent(ehebrnmntEvent);
    }

    public void modifyEhebrnmnt(Ehebrnmnt ehebrnmnt) {
        /* Gen by Vizend Studio v6.0.0 */
        Ehebrnmnt oldEhebrnmnt = findEhebrnmnt(ehebrnmnt.getId());
        NameValueList nameValues = Entities.getModifiedNameValues(oldEhebrnmnt, ehebrnmnt);
        if (nameValues.size() > 0) {
            modifyEhebrnmnt(ehebrnmnt.getId(), nameValues);
        }
    }

    public void removeEhebrnmnt(String ehebrnmntId) {
        /* Gen by Vizend Studio v6.0.0 */
        Ehebrnmnt ehebrnmnt = findEhebrnmnt(ehebrnmntId);
        ehebrnmntStore.delete(ehebrnmnt);
        EhebrnmntEvent ehebrnmntEvent = EhebrnmntEvent.newEhebrnmntRemovedEvent(ehebrnmnt, ehebrnmnt.getId());
        eventProxy.publishEvent(ehebrnmntEvent);
    }

    public boolean existsEhebrnmnt(String ehebrnmntId) {
        /* Gen by Vizend Studio v6.0.0 */
        return ehebrnmntStore.exists(ehebrnmntId);
    }

    public void handleEventForProjection(EhebrnmntEvent ehebrnmntEvent) {
        /* Gen by Vizend Studio v6.0.0 */
        switch(ehebrnmntEvent.getDataEventType()) {
            case Registered:
                ehebrnmntStore.create(ehebrnmntEvent.getEhebrnmnt());
                break;
            case Modified:
                Ehebrnmnt ehebrnmnt = ehebrnmntStore.retrieve(ehebrnmntEvent.getEhebrnmntId());
                ehebrnmnt.modify(ehebrnmntEvent.getNameValues());
                ehebrnmntStore.update(ehebrnmnt);
                break;
            case Removed:
                ehebrnmntStore.delete(ehebrnmntEvent.getEhebrnmntId());
                break;
        }
    }

    public Ehebrnmnt findByNmntDsmsSn(Long nmntDsmsSn) {
        /* Gen by Vizend Studio v6.0.0 */
        return ehebrnmntOptionStore.retrieveByNmntDsmsSn(nmntDsmsSn);
    }

    public List<Ehebrnmnt> findByNmntDtBetween(String nmntStDt, String nmntEndDt) {
        /* Gen by Vizend Studio v6.0.0 */
        return ehebrnmntOptionStore.retrieveByNmntDtBetween(nmntStDt, nmntEndDt);
    }

//    public List<Ehebrnmnt> findByEmpnoIn(List<String> empnos) {
//        /* Gen by Vizend Studio v6.0.0 */
//        return ehebrnmntOptionStore.retrieveByEmpnoIn(empnos);
//    }

    public Ehebrnmnt findTopByNmntDsmsSnBetweenOrderByNmntDsmsSnDesc(Long fromNmntDsmsSn, Long toNmntDsmsSn) {
        /* Gen by Vizend Studio v6.0.0 */
        return ehebrnmntOptionStore.retrieveTopByNmntDsmsSnBetweenOrderByNmntDsmsSnDesc(fromNmntDsmsSn, toNmntDsmsSn);
    }
}
