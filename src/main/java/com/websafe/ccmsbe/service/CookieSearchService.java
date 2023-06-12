package com.websafe.ccmsbe.service;

import com.websafe.ccmsbe.entity.Cookie;
import com.websafe.ccmsbe.entity.Website;
import com.websafe.ccmsbe.repository.CookieRepository;
import com.websafe.ccmsbe.repository.WebsiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CookieSearchService {
    private final CookieRepository cookieRepository;

    @Autowired
    private WebsiteRepository websiteRepo;

    @Autowired
    public CookieSearchService(CookieRepository cookieRepository) {
        this.cookieRepository = cookieRepository;
    }



    public List<Cookie> searchAndSaveCookies(Long websiteId) {
        Website website = websiteRepo.findById(websiteId).orElse(null);
        if (website != null) {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Void> responseEntity = restTemplate.getForEntity(website.getDomain(), Void.class);

            HttpHeaders headers = responseEntity.getHeaders();
            List<String> cookieHeaders = headers.get(HttpHeaders.SET_COOKIE);

            List<Cookie> cookies = new ArrayList<>();
            if (cookieHeaders != null) {
                for (String cookieHeader : cookieHeaders) {
                    String[] cookieParts = cookieHeader.split(";");

                    String name = null;
//                    String value = null;
//                    String expires = null;
                    String domain = null;
                    String path = null;

                    String[] cookieNames = cookieParts[0].split("=");
                    name=cookieNames[0];

                    for (String part : cookieParts) {
                        String[] nameValue = part.trim().split("=");
                        if (nameValue.length == 2) {
                            String paramName = nameValue[0].trim();
                            String paramValue = nameValue[1].trim();
                            switch (paramName.toLowerCase()) {
//                                case "expires":
//                                    expires = paramValue;
//                                    break;
                                case "domain":
                                    domain = paramValue;
                                    break;
                                case "path":
                                    path = paramValue;
                                    break;
                                default:
                                    break;
                            }
                        }
                    }

                    Cookie cookieEntity = new Cookie();
                    cookieEntity.setCookieName(name);
    //                cookieEntity.setValue(value);
    //                cookieEntity.setExpires(expires);
                    cookieEntity.setDomain(domain);
                    cookieEntity.setPath(path);
                    cookieEntity.setWebsite(website);

                    cookies.add(cookieEntity);
                    cookieRepository.save(cookieEntity);
                }
            }

            return cookies;
        }
        return null;
    }
}
