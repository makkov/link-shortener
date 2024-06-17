package com.colvir.link.shortener.mapper;

import com.colvir.link.shortener.dto.GenerateLinkResponse;
import com.colvir.link.shortener.dto.LinkPageResponse;
import com.colvir.link.shortener.dto.LinkResponse;
import com.colvir.link.shortener.dto.UpdateLinkRequest;
import com.colvir.link.shortener.model.Link;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface LinkMapper {

    GenerateLinkResponse linkToGenerateLinkResponse(Link link);

    LinkResponse linkToLinkResponse(Link link);

    List<LinkResponse> linksToLinkResponse(List<Link> links);

    @Mapping(target = "status", ignore = true)
    Link updateLinkRequestToLink(UpdateLinkRequest request);

    default LinkPageResponse linksToLinkPageResponse(List<Link> links) {
        List<LinkResponse> linkResponses = linksToLinkResponse(links);
        return new LinkPageResponse(linkResponses);
    }
}
