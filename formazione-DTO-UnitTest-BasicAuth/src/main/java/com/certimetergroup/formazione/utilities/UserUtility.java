package com.certimetergroup.formazione.utilities;

import com.certimetergroup.formazione.dto.DTOUser;
import org.springframework.util.StringUtils;

public class UserUtility {

    public static boolean isValidForInsert(DTOUser dtoUser) {
        /*** decidiamo di rendere obbligatori solo 'firstname' e 'lastname' ***/
        if( dtoUser.getIdUser()!=null ) return false;
        if( StringUtils.isEmpty(dtoUser.getFirstname()) )	return false;
        if( StringUtils.isEmpty(dtoUser.getLastname()) )	return false;
        return true;
    }

}
