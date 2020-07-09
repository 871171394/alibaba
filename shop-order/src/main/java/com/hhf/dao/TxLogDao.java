package com.hhf.dao;


import com.hhf.domain.TxLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TxLogDao extends JpaRepository<TxLog, String> {
}
