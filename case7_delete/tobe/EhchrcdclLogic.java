/*
 COPYRIGHT (c) NEXTREE Inc. 2014
 This software is the proprietary of NEXTREE Inc.
 @since 2014. 6. 10.
*/
package kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.logic;

import io.vizend.accent.domain.type.NameValueList;
import io.vizend.accent.util.entity.Entities;
import io.vizend.prologue.janitor.proxy.EventProxy;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.entity.Ehchrcdcl;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.entity.sdo.EhchrcdclCdo;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.domain.event.EhchrcdclEvent;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.store.EhchrcdclOptionStore;
import kr.amc.amis.ehs.aggregate.disasterpreparednesstraining.store.EhchrcdclStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class EhchrcdclLogic {
    /* Gen by Vizend Studio v5.1.0 */
    private final EhchrcdclStore ehchrcdclStore;
    private final EhchrcdclOptionStore ehchrcdclOptionStore;
    private final EventProxy eventProxy;

    public EhchrcdclLogic(EhchrcdclStore ehchrcdclStore, EhchrcdclOptionStore ehchrcdclOptionStore, EventProxy eventProxy) {
        /* Gen by Vizend Studio v5.1.0 */
        this.ehchrcdclStore = ehchrcdclStore;
        this.ehchrcdclOptionStore = ehchrcdclOptionStore;
        this.eventProxy = eventProxy;
    }

    public String registerEhchrcdcl(EhchrcdclCdo ehchrcdclCdo) {
        /* Gen by Vizend Studio v5.1.0 */
        Ehchrcdcl ehchrcdcl = new Ehchrcdcl(ehchrcdclCdo);
        if (ehchrcdclCdo.hasAdditionalAttributes()) {
            ehchrcdcl.modify(ehchrcdclCdo.getAdditionalAttributes());
        }
        if (ehchrcdclStore.exists(ehchrcdcl.getId())) {
            throw new IllegalArgumentException("ehchrcdcl already exists. " + ehchrcdcl.getId());
        }
        ehchrcdclStore.create(ehchrcdcl);
        EhchrcdclEvent ehchrcdclEvent = EhchrcdclEvent.newEhchrcdclRegisteredEvent(ehchrcdcl, ehchrcdcl.getId());
        eventProxy.publishEvent(ehchrcdclEvent);
        return ehchrcdcl.getId();
    }

    public List<String> registerEhchrcdcls(List<EhchrcdclCdo> ehchrcdclCdos) {
        /* Gen by Vizend Studio v5.1.0 */
        return ehchrcdclCdos.stream().map(this::registerEhchrcdcl).toList();
    }

    public Ehchrcdcl findEhchrcdcl(String ehchrcdclId) {
        /* Gen by Vizend Studio v5.1.0 */
        Ehchrcdcl ehchrcdcl = ehchrcdclStore.retrieve(ehchrcdclId);
        if (ehchrcdcl == null) {
            throw new NoSuchElementException("Ehchrcdcl id: " + ehchrcdclId);
        }
        return ehchrcdcl;
    }

    public void modifyEhchrcdcl(String ehchrcdclId, NameValueList nameValues) {
        /* Gen by Vizend Studio v5.1.0 */
        Ehchrcdcl ehchrcdcl = findEhchrcdcl(ehchrcdclId);
        ehchrcdcl.modify(nameValues);
        ehchrcdclStore.update(ehchrcdcl);
        EhchrcdclEvent ehchrcdclEvent = EhchrcdclEvent.newEhchrcdclModifiedEvent(ehchrcdclId, nameValues, ehchrcdcl);
        eventProxy.publishEvent(ehchrcdclEvent);
    }

    public void modifyEhchrcdcl(Ehchrcdcl ehchrcdcl) {
        /* Gen by Vizend Studio v5.1.0 */
        Ehchrcdcl oldEhchrcdcl = findEhchrcdcl(ehchrcdcl.getId());
        NameValueList nameValues = Entities.getModifiedNameValues(oldEhchrcdcl, ehchrcdcl);
        if (nameValues.size() > 0) {
            modifyEhchrcdcl(ehchrcdcl.getId(), nameValues);
        }
    }

    public void removeEhchrcdcl(String ehchrcdclId) {
        /* Gen by Vizend Studio v5.1.0 */
        Ehchrcdcl ehchrcdcl = findEhchrcdcl(ehchrcdclId);
        ehchrcdclStore.delete(ehchrcdcl);
        EhchrcdclEvent ehchrcdclEvent = EhchrcdclEvent.newEhchrcdclRemovedEvent(ehchrcdcl, ehchrcdcl.getId());
        eventProxy.publishEvent(ehchrcdclEvent);
    }

    public boolean existsEhchrcdcl(String ehchrcdclId) {
        /* Gen by Vizend Studio v5.1.0 */
        return ehchrcdclStore.exists(ehchrcdclId);
    }

    public void handleEventForProjection(EhchrcdclEvent ehchrcdclEvent) {
        /* Gen by Vizend Studio v5.1.0 */
        switch(ehchrcdclEvent.getDataEventType()) {
            case Registered:
                ehchrcdclStore.create(ehchrcdclEvent.getEhchrcdcl());
                break;
            case Modified:
                Ehchrcdcl ehchrcdcl = ehchrcdclStore.retrieve(ehchrcdclEvent.getEhchrcdclId());
                ehchrcdcl.modify(ehchrcdclEvent.getNameValues());
                ehchrcdclStore.update(ehchrcdcl);
                break;
            case Removed:
                ehchrcdclStore.delete(ehchrcdclEvent.getEhchrcdclId());
                break;
        }
    }

    public Ehchrcdcl findTopByEhsPlanManageSnAndEhsDtlPlanManageSnAndCircumBhvrKnackSnOrderBySnDesc(Long ehsPlanManageSn, Long ehsDtlPlanManageSn, Long circumBhvrKnackSn) {
        /* Gen by Vizend Studio v5.1.0 */
        return ehchrcdclOptionStore.retrieveTopByEhsPlanManageSnAndEhsDtlPlanManageSnAndCircumBhvrKnackSnOrderBySnDesc(ehsPlanManageSn, ehsDtlPlanManageSn, circumBhvrKnackSn);
    }

    public Ehchrcdcl findByEhsPlanManageSnAndEhsDtlPlanManageSnAndCircumBhvrKnackSnAndSn(Long ehsPlanManageSn, Long ehsDtlPlanManageSn, Long circumBhvrKnackSn, Long sn) {
        /* Gen by Vizend Studio v5.1.0 */
        return ehchrcdclOptionStore.retrieveByEhsPlanManageSnAndEhsDtlPlanManageSnAndCircumBhvrKnackSnAndSn(ehsPlanManageSn, ehsDtlPlanManageSn, circumBhvrKnackSn, sn);
    }

    public List<Ehchrcdcl> findByEhsPlanManageSnAndEhsDtlPlanManageSnAndCircumBhvrKnackSn(Long ehsPlanManageSn, Long ehsDtlPlanManageSn, Long circumBhvrKnackSn) {
        /* Gen by Vizend Studio v5.1.0 */
        return ehchrcdclOptionStore.retrieveByEhsPlanManageSnAndEhsDtlPlanManageSnAndCircumBhvrKnackSn(ehsPlanManageSn, ehsDtlPlanManageSn, circumBhvrKnackSn);
    }
}
