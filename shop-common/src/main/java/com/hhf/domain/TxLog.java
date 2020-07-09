package com.hhf.domain;


import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;


/**
 * @author Huang.Hua.Fu
 * @date 2020/7/9 14:57
 */
@Entity(name = "shop_txlog")
@Data
public class TxLog {
    @Id
    private String txLogId;
    private String content;
    private LocalDateTime date;
}
