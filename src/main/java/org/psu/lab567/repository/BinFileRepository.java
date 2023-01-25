package org.psu.lab567.repository;

import org.psu.lab567.model.BinFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BinFileRepository extends JpaRepository<BinFile, Long> {
}
