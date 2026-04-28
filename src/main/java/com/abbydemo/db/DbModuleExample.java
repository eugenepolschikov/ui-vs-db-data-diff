package com.abbydemo.db;

import com.abbydemo.models.PhiDto;
import java.util.Set;

public final class DbModuleExample {

    private DbModuleExample() {
    }

    public static Set<PhiDto> loadPhiRecordsFromDb() {
        DbConfig config = DbConfig.fromSystemProperties();
        MySqlPhiRepository repository = new MySqlPhiRepository(config);
        return repository.fetchPhiRecords("SELECT id, description, timestamp, severity FROM phi_records");
    }
}
