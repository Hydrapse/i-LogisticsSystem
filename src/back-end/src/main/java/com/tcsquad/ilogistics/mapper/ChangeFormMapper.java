package com.tcsquad.ilogistics.mapper;

import com.tcsquad.ilogistics.domain.ChangeForm;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChangeFormMapper {
    List<ChangeForm> getChangeForms();

    ChangeForm getChangeFormByChangId(int changeId);

    void insertChangeForm(ChangeForm changeForm);

    void updateChangeFormStatus(String status,int changeId);

    void deleteChangeForm(int changeId);
}
