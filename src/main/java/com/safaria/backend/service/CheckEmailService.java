package com.safaria.backend.service;

import org.springframework.stereotype.Service;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.Record;
import org.xbill.DNS.TextParseException;
import org.xbill.DNS.Type;
import com.safaria.backend.CustomExceptions.InvalidDomainException;

@Service
public class CheckEmailService {

    public boolean isValidEmailDomain(String domain) {
        
        try
        {
        Record[] records = new Lookup(domain, Type.MX).run();
        return records != null && records.length > 0;
        }
        catch(TextParseException ex )
        {
            throw new InvalidDomainException("Invalid email domain :"+domain, ex);
        }
        
    }

}
