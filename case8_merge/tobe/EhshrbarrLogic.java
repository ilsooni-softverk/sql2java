/*
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package kr.amc.amis.ehs.aggregate.accident.domain.logic;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import kr.amc.amis.ehs.aggregate.accident.store.EhshrbarrStore;
import kr.amc.amis.ehs.aggregate.accident.store.EhshrbarrOptionStore;
import io.vizend.prologue.janitor.proxy.EventProxy;
import kr.amc.amis.ehs.aggregate.accident.domain.entity.sdo.EhshrbarrCdo;
import kr.amc.amis.ehs.aggregate.accident.domain.event.EhshrbarrEvent;
import java.util.List;

import kr.amc.amis.ehs.aggregate.accident.domain.entity.Ehshrbarr;
import java.util.NoSuchElementException;
import io.vizend.accent.domain.type.NameValueList;
import io.vizend.accent.util.entity.Entities;
import java.time.LocalDateTime;

@Service
@Transactional
public class EhshrbarrLogic {
    /* Gen by Vizend Studio v6.0.0 */
    private final EhshrbarrStore ehshrbarrStore;
    private final EhshrbarrOptionStore ehshrbarrOptionStore;
    private final EventProxy eventProxy;

    public EhshrbarrLogic(EhshrbarrStore ehshrbarrStore, EhshrbarrOptionStore ehshrbarrOptionStore, EventProxy eventProxy) {
        /* Gen by Vizend Studio v6.0.0 */
        this.ehshrbarrStore = ehshrbarrStore;
        this.ehshrbarrOptionStore = ehshrbarrOptionStore;
        this.eventProxy = eventProxy;
    }

    public String registerEhshrbarr(EhshrbarrCdo ehshrbarrCdo) {
        /* Gen by Vizend Studio v6.0.0 */
        Ehshrbarr ehshrbarr = new Ehshrbarr(ehshrbarrCdo);
        if (ehshrbarrCdo.hasAdditionalAttributes()) {
            ehshrbarr.modify(ehshrbarrCdo.getAdditionalAttributes());
        }
        if (ehshrbarrStore.exists(ehshrbarr.getId())) {
            throw new IllegalArgumentException("ehshrbarr already exists. " + ehshrbarr.getId());
        }
        ehshrbarrStore.create(ehshrbarr);
        EhshrbarrEvent ehshrbarrEvent = EhshrbarrEvent.newEhshrbarrRegisteredEvent(ehshrbarr, ehshrbarr.getId());
        eventProxy.publishEvent(ehshrbarrEvent);
        return ehshrbarr.getId();
    }

    public List<String> registerEhshrbarrs(List<EhshrbarrCdo> ehshrbarrCdos) {
        /* Gen by Vizend Studio v6.0.0 */
        return ehshrbarrCdos.stream().map(this::registerEhshrbarr).toList();
    }

    public Ehshrbarr findEhshrbarr(String ehshrbarrId) {
        /* Gen by Vizend Studio v6.0.0 */
        Ehshrbarr ehshrbarr = ehshrbarrStore.retrieve(ehshrbarrId);
        if (ehshrbarr == null) {
            throw new NoSuchElementException("Ehshrbarr id: " + ehshrbarrId);
        }
        return ehshrbarr;
    }

    public void modifyEhshrbarr(String ehshrbarrId, NameValueList nameValues) {
        /* Gen by Vizend Studio v6.0.0 */
        Ehshrbarr ehshrbarr = findEhshrbarr(ehshrbarrId);
        ehshrbarr.modify(nameValues);
        ehshrbarrStore.update(ehshrbarr);
        EhshrbarrEvent ehshrbarrEvent = EhshrbarrEvent.newEhshrbarrModifiedEvent(ehshrbarrId, nameValues, ehshrbarr);
        eventProxy.publishEvent(ehshrbarrEvent);
    }

    public void modifyEhshrbarr(Ehshrbarr ehshrbarr) {
        /* Gen by Vizend Studio v6.0.0 */
        Ehshrbarr oldEhshrbarr = findEhshrbarr(ehshrbarr.getId());
        NameValueList nameValues = Entities.getModifiedNameValues(oldEhshrbarr, ehshrbarr);
        if (nameValues.size() > 0) {
            modifyEhshrbarr(ehshrbarr.getId(), nameValues);
        }
    }

    public void removeEhshrbarr(String ehshrbarrId) {
        /* Gen by Vizend Studio v6.0.0 */
        Ehshrbarr ehshrbarr = findEhshrbarr(ehshrbarrId);
        ehshrbarrStore.delete(ehshrbarr);
        EhshrbarrEvent ehshrbarrEvent = EhshrbarrEvent.newEhshrbarrRemovedEvent(ehshrbarr, ehshrbarr.getId());
        eventProxy.publishEvent(ehshrbarrEvent);
    }

    public boolean existsEhshrbarr(String ehshrbarrId) {
        /* Gen by Vizend Studio v6.0.0 */
        return ehshrbarrStore.exists(ehshrbarrId);
    }

    public void handleEventForProjection(EhshrbarrEvent ehshrbarrEvent) {
        /* Gen by Vizend Studio v6.0.0 */
        switch(ehshrbarrEvent.getDataEventType()) {
            case Registered:
                ehshrbarrStore.create(ehshrbarrEvent.getEhshrbarr());
                break;
            case Modified:
                Ehshrbarr ehshrbarr = ehshrbarrStore.retrieve(ehshrbarrEvent.getEhshrbarrId());
                ehshrbarr.modify(ehshrbarrEvent.getNameValues());
                ehshrbarrStore.update(ehshrbarr);
                break;
            case Removed:
                ehshrbarrStore.delete(ehshrbarrEvent.getEhshrbarrId());
                break;
        }
    }

    public List<Ehshrbarr> findByAccidDclarRcepDivCd(String accidDclarRcepDivCd) {
        /* Gen by Vizend Studio v6.0.0 */
        return ehshrbarrOptionStore.retrieveByAccidDclarRcepDivCd(accidDclarRcepDivCd);
    }

    public List<Ehshrbarr> findByAccidDclarNo(String accidDclarNo) {
        /* Gen by Vizend Studio v6.0.0 */
        return ehshrbarrOptionStore.retrieveByAccidDclarNo(accidDclarNo);
    }

    public List<Ehshrbarr> findByAccidDclarRcepDivCdAndAccidDclarNoIn(String accidDclarRcepDivCd, List<String> accidDclarNos) {
        /* Gen by Vizend Studio v6.0.0 */
        return ehshrbarrOptionStore.retrieveByAccidDclarRcepDivCdAndAccidDclarNoIn(accidDclarRcepDivCd, accidDclarNos);
    }

    public List<Ehshrbarr> findByAccidDclarRcepDivCdAndOpadMdexYn(String accidDclarRcepDivCd, String opadMdexYn) {
        /* Gen by Vizend Studio v6.0.0 */
        return ehshrbarrOptionStore.retrieveByAccidDclarRcepDivCdAndOpadMdexYn(accidDclarRcepDivCd, opadMdexYn);
    }

    public Ehshrbarr findByAccidDclarRcepDivCdAndAccidDclarNo(String accidDclarRcepDivCd, String accidDclarNo) {
        /* Gen by Vizend Studio v6.0.0 */
        return ehshrbarrOptionStore.retrieveByAccidDclarRcepDivCdAndAccidDclarNo(accidDclarRcepDivCd, accidDclarNo);
    }

    public List<Ehshrbarr> findByAccidDclarRcepDivCdInAndAccidDclarNo(List<String> accidDclarRcepDivCds, String accidDclarNo) {
        /* Gen by Vizend Studio v6.0.0 */
        return ehshrbarrOptionStore.retrieveByAccidDclarRcepDivCdInAndAccidDclarNo(accidDclarRcepDivCds, accidDclarNo);
    }

    public List<Ehshrbarr> findByAccidDclarRcepDivCdAndTkactFinshDtmGreaterThanEqualAndTkactFinshDtmLessThanEqual(String accidDclarRcepDivCd, LocalDateTime stDtm, LocalDateTime endDtm) {
        /* Gen by Vizend Studio v6.0.0 */
        return ehshrbarrOptionStore.retrieveByAccidDclarRcepDivCdAndTkactFinshDtmGreaterThanEqualAndTkactFinshDtmLessThanEqual(accidDclarRcepDivCd, stDtm, endDtm);
    }

    public List<Ehshrbarr> findByAccidDclarRcepDivCdAndOpadMdexYnAndAccidOccurDtmGreaterThanEqualAndAccidOccurDtmLessThanEqual(String accidDclarRcepDivCd, String opadMdexYn, LocalDateTime stDtm, LocalDateTime endDtm) {
        /* Gen by Vizend Studio v6.0.0 */
        return ehshrbarrOptionStore.retrieveByAccidDclarRcepDivCdAndOpadMdexYnAndAccidOccurDtmGreaterThanEqualAndAccidOccurDtmLessThanEqual(accidDclarRcepDivCd, opadMdexYn, stDtm, endDtm);
    }
}
