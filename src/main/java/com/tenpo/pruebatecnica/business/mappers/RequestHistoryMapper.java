package com.tenpo.pruebatecnica.business.mappers;


import com.tenpo.pruebatecnica.business.wrappers.RequestHistoryWrapper;
import com.tenpo.pruebatecnica.repository.entities.RequestHistoryEntity;
import com.tenpo.pruebatecnica.utils.Constants;
import com.tenpo.pruebatecnica.web.models.RequestHistoryResponse;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", imports = LocalDateTime.class)
public interface RequestHistoryMapper {

  @Mapping(target = "parameters", qualifiedByName = "buildParameterMap", source = "parameters")
  RequestHistoryResponse mapToRequestHistoryResponse(RequestHistoryEntity entity);

  @Mapping(target = "date", expression = "java(LocalDateTime.now())")
  @Mapping(target = "parameters", qualifiedByName = "buildParameters", source = "parameters")
  @Mapping(target = "id", ignore = true)
  RequestHistoryEntity mapToRequestHistoryEntity(RequestHistoryWrapper requestHistoryWrapper);

  @Named("buildParameters")
  default String buildParameters(List<BigDecimal> parameters) {
    return parameters.stream().map(BigDecimal::toString).collect(Collectors.joining(","));
  }

  @Named("buildParameterMap")
  default Map<String, BigDecimal> buildParameterMap(String parameters) {
    return Optional.ofNullable(parameters)
        .map(list -> Arrays.asList(parameters.split(",")))
        .map(parameter -> Map.of(
            Constants.FIRST_PARAMETER, new BigDecimal(parameter.getFirst()),
            Constants.SECOND_PARAMETER, new BigDecimal(parameter.get(1))))
        .orElse(null);
  }
}
