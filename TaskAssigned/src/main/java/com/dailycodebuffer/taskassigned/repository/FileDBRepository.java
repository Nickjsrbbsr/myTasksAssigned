package com.dailycodebuffer.taskassigned.repository;

import com.dailycodebuffer.taskassigned.model.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {

}