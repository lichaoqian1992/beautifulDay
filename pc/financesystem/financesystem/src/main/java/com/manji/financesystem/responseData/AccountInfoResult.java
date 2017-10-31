package com.manji.financesystem.responseData;

import com.manji.financesystem.common.AccountTypeEnum;
import lombok.Data;

/**
 * Created by pudding on 2017-1-17.
 */
@Data
public class AccountInfoResult {

    private String accountName;

    private double amount;

    private AccountTypeEnum type;

}
