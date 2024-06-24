package com.colvir.link.shortener.mapper;

import com.colvir.link.shortener.dto.GenerateLinkResponse;
import com.colvir.link.shortener.dto.LinkPageResponse;
import com.colvir.link.shortener.dto.LinkResponse;
import com.colvir.link.shortener.dto.UpdateLinkRequest;
import com.colvir.link.shortener.model.Link;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface LinkMapper {

    GenerateLinkResponse linkToGenerateLinkResponse(Link link);

    LinkResponse linkToLinkResponse(Link link);

    List<LinkResponse> linksToLinkResponse(List<Link> links);

    default Link updateLinkRequestToLink(@MappingTarget Link linkForUpdate, UpdateLinkRequest request) {
        String shorted = request.getShorted();
        if (shorted != null) {
            linkForUpdate.setShorted(shorted);
        }

        String original = request.getOriginal();
        if (original != null) {
            linkForUpdate.setOriginal(original);
        }
        return linkForUpdate;
    }

    default LinkPageResponse linksToLinkPageResponse(List<Link> links) {
        List<LinkResponse> linkResponses = linksToLinkResponse(links);
        return new LinkPageResponse(linkResponses);
    }
}
