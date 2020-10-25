package com.garancebenat.whois.component;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Map;

@AllArgsConstructor
@Data
public class DnsRecords {
    private Map<String, ArrayList<String>> attributes;
}


