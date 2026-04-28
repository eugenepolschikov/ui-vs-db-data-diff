package com.abbydemo.models;

import java.util.Date;
import java.util.Objects;

public class PhiDto {
    private Integer id;
    private String description;
    private Date timestamp;
    private String severity;

    public PhiDto(Integer id, String description, Date timestamp, String severity) {
        this.id = id;
        this.description = description;
        this.timestamp = timestamp;
        this.severity = severity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    @Override
    public String toString() {
        return "PhiDto{"
                + "id=" + id
                + ", description='" + description + '\''
                + ", timestamp=" + timestamp
                + ", severity='" + severity + '\''
                + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PhiDto phiDto = (PhiDto) obj;
        return Objects.equals(id, phiDto.id)
                && Objects.equals(description, phiDto.description)
                && Objects.equals(timestamp, phiDto.timestamp)
                && Objects.equals(severity, phiDto.severity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, timestamp, severity);
    }
}
