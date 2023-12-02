package com.bmw.cc.servicedemandidentifier.api.calltopic.entity;

import java.util.Objects;

import com.bmw.cc.servicedemandidentifier.spring.exception.BusinessException;
import com.bmw.cc.servicedemandidentifier.web.logging.entity.LogErrorId;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.micrometer.core.instrument.util.StringUtils;
import io.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SnsEnvelope {

    @ApiModelProperty("Message")
    private final String message;

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final SnsEnvelope that = (SnsEnvelope) o;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @JsonCreator
    public SnsEnvelope(@JsonProperty(value = "Message", required = true) final String message) {
        if (StringUtils.isBlank(message)) {
            throw new BusinessException(LogErrorId.INTERNAL_ERROR, "The parsed TopicMessage is blank.");
        }
        this.message = message;
    }
}
