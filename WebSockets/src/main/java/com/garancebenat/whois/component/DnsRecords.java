package com.garancebenat.whois.component;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Data
public class DnsRecords {
    private Map<String, List<String>> attributes;
}


