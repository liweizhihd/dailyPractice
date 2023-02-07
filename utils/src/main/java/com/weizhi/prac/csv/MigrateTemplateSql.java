package com.weizhi.prac.csv;

import com.weizhi.prac.csv.model.Template;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class MigrateTemplateSql {
    public static Map<String, String> INDUSTRIES_MAP_VERTICAL = new HashMap<>();

    // supported verticals
    public static Map<String, Integer> VERTICAL_MAP = new HashMap<>();

    public static Map<String, String> TEMPLATE_NAME_CONTENT_VERTIVAL_MAP_TEMPLATEUUID = new HashMap<>();
    public static Map<String, String> TEMPLATE_NAME_CONTENT_MAP_TEMPLATEUUID = new HashMap<>();

    public static Map<String, String> DELTAL_REPLACE_MAP = new HashMap();

    public static Set<String> DENTAL_TEMPLATE = new HashSet<>();

    static {
        DELTAL_REPLACE_MAP.put("[CustomerFirstName]", "{{PatientFirstName}}");
        DELTAL_REPLACE_MAP.put("[BusinessName]", "{{PracticeName}}");
        DELTAL_REPLACE_MAP.put("[BusinessPhone]", "{{PracticePhone}}");

        // supported verticals
        VERTICAL_MAP.put("salon", 17);
        VERTICAL_MAP.put("spa", 18);
        VERTICAL_MAP.put("dental", 19);
        VERTICAL_MAP.put("vet", 20);
        VERTICAL_MAP.put("gen_med", 32);
        VERTICAL_MAP.put("chiro", 33);
        VERTICAL_MAP.put("opto", 34);
        VERTICAL_MAP.put("auto", 35);
        VERTICAL_MAP.put("derm", 39);
        VERTICAL_MAP.put("med_spa", 51);

        //
        INDUSTRIES_MAP_VERTICAL.put("0", "smb");
        INDUSTRIES_MAP_VERTICAL.put("1", "auto");
        INDUSTRIES_MAP_VERTICAL.put("2", "dental");
        INDUSTRIES_MAP_VERTICAL.put("3", "spa");
        INDUSTRIES_MAP_VERTICAL.put("4", "chiro");
        INDUSTRIES_MAP_VERTICAL.put("10", "dental");
        INDUSTRIES_MAP_VERTICAL.put("11", "vet");
        INDUSTRIES_MAP_VERTICAL.put("12", "opto");
        INDUSTRIES_MAP_VERTICAL.put("13", "salon");
        INDUSTRIES_MAP_VERTICAL.put("14", "auto");
        INDUSTRIES_MAP_VERTICAL.put("15", "gen_med");
        INDUSTRIES_MAP_VERTICAL.put("16", "gen_med");
        INDUSTRIES_MAP_VERTICAL.put("17", "gen_med");
        INDUSTRIES_MAP_VERTICAL.put("18", "gen_med");
        INDUSTRIES_MAP_VERTICAL.put("19", "auto");
        INDUSTRIES_MAP_VERTICAL.put("20", "auto");
        INDUSTRIES_MAP_VERTICAL.put("21", "derm");
        INDUSTRIES_MAP_VERTICAL.put("22", "opto");
        INDUSTRIES_MAP_VERTICAL.put("23", "smb");
        INDUSTRIES_MAP_VERTICAL.put("24", "smb");
        INDUSTRIES_MAP_VERTICAL.put("25", "med_spa");
        INDUSTRIES_MAP_VERTICAL.put("26", "gen_med");
        INDUSTRIES_MAP_VERTICAL.put("27", "spa");
        INDUSTRIES_MAP_VERTICAL.put("28", "gen_med");
        INDUSTRIES_MAP_VERTICAL.put("29", "smb");
        INDUSTRIES_MAP_VERTICAL.put("30", "smb");
        INDUSTRIES_MAP_VERTICAL.put("31", "gen_med");
        INDUSTRIES_MAP_VERTICAL.put("32", "gen_med");
        INDUSTRIES_MAP_VERTICAL.put("33", "gen_med");
        INDUSTRIES_MAP_VERTICAL.put("34", "gen_med");
        INDUSTRIES_MAP_VERTICAL.put("35", "gen_med");
        INDUSTRIES_MAP_VERTICAL.put("36", "gen_med");
        INDUSTRIES_MAP_VERTICAL.put("37", "gen_med");
        INDUSTRIES_MAP_VERTICAL.put("38", "gen_med");
        INDUSTRIES_MAP_VERTICAL.put("39", "gen_med");
        INDUSTRIES_MAP_VERTICAL.put("40", "gen_med");
        INDUSTRIES_MAP_VERTICAL.put("41", "gen_med");
        INDUSTRIES_MAP_VERTICAL.put("42", "gen_med");
        INDUSTRIES_MAP_VERTICAL.put("43", "smb");
        INDUSTRIES_MAP_VERTICAL.put("44", "vet");
        INDUSTRIES_MAP_VERTICAL.put("45", "smb");
        INDUSTRIES_MAP_VERTICAL.put("46", "smb");
        INDUSTRIES_MAP_VERTICAL.put("47", "smb");
        INDUSTRIES_MAP_VERTICAL.put("48", "smb");
        INDUSTRIES_MAP_VERTICAL.put("49", "smb");
        INDUSTRIES_MAP_VERTICAL.put("50", "smb");
        INDUSTRIES_MAP_VERTICAL.put("51", "smb");
        INDUSTRIES_MAP_VERTICAL.put("52", "smb");
        INDUSTRIES_MAP_VERTICAL.put("53", "smb");
        INDUSTRIES_MAP_VERTICAL.put("54", "smb");
        INDUSTRIES_MAP_VERTICAL.put("55", "smb");
        INDUSTRIES_MAP_VERTICAL.put("56", "smb");
        INDUSTRIES_MAP_VERTICAL.put("57", "smb");
        INDUSTRIES_MAP_VERTICAL.put("58", "smb");
        INDUSTRIES_MAP_VERTICAL.put("59", "smb");
    }


    public static void main(String[] args) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("file/one-off-templates.csv");
        InputStream inputStream = classPathResource.getInputStream();
        List<Template> templateList = CsvUtil.getCsvDataMethod(inputStream, Template.class);
        List<String> sqlList = new LinkedList<>();

        for (Template template : templateList) {
            String verticalStr = INDUSTRIES_MAP_VERTICAL.get(template.getIndustry());
            if ("dental".equals(verticalStr)) {
                DENTAL_TEMPLATE.add(template.getName() + template.getContent());
            }
        }
        for (Template template : templateList) {
            genSql(template, sqlList);
        }

        System.out.println("----------------------");
        for (String s : sqlList) {
            System.out.println(s);
        }
        System.out.println("----------------------");
    }

    public static void genSql(Template template, List<String> sqlList) {
        String verticalStr = INDUSTRIES_MAP_VERTICAL.get(template.getIndustry());
        Integer verticalId = VERTICAL_MAP.get(verticalStr);
        if (verticalId == null) {
            // vertical not supported
            return;
        }
        String templateMapKey = template.getName() + template.getContent();
        String templateUuid = TEMPLATE_NAME_CONTENT_MAP_TEMPLATEUUID.get(templateMapKey);
        if (templateUuid == null) {
            templateUuid = UUID.randomUUID().toString();
            TEMPLATE_NAME_CONTENT_MAP_TEMPLATEUUID.put(templateMapKey, templateUuid);
            String content = getContentConvertedTokens(template, verticalStr);
            String templateSql = "INSERT INTO template2.text_template " +
                    "(template_name, business_guid, template_content, text_template_guid) " +
                    "VALUES ('" + template.getName() + "', '00000000-0000-0000-0000-000000000000'::uuid, '" + content + "', '" + templateUuid + "'::uuid);\n";
            sqlList.add(templateSql);

            // hot list group
            String groupHotlistSql = "INSERT INTO template2.text_template_group " +
                    "(text_template_id, item_group_id, business_guid) " +
                    "SELECT id, 77, '00000000-0000-0000-0000-000000000000'::uuid " +
                    "FROM template2.text_template " +
                    "WHERE text_template_guid = '" + templateUuid + "'::uuid;\n";
            sqlList.add(groupHotlistSql);
        }
        String templateVerticalMapKey = templateMapKey + verticalId;
        String templateUuidFromVerticle = TEMPLATE_NAME_CONTENT_VERTIVAL_MAP_TEMPLATEUUID.get(templateVerticalMapKey);
        if (templateUuidFromVerticle == null) {
            TEMPLATE_NAME_CONTENT_VERTIVAL_MAP_TEMPLATEUUID.put(templateVerticalMapKey, templateUuid);
            // vertical group
            String groupVerticalSql = "INSERT INTO template2.text_template_group " +
                    "(text_template_id, item_group_id, business_guid) " +
                    "SELECT id, " + verticalId + ", '00000000-0000-0000-0000-000000000000'::uuid " +
                    "FROM template2.text_template " +
                    "WHERE text_template_guid = '" + templateUuid + "'::uuid;\n";
            sqlList.add(groupVerticalSql);
        }
    }

    public static String getContentConvertedTokens(Template template, String verticalStr) {
        String content = template.getContent().replace("'", "''");
        // replace token such as CustomerFirstName->PatientFirstName
        if ("dental".equals(verticalStr) || DENTAL_TEMPLATE.contains(template.getName() + template.getContent())) {
            for (Map.Entry<String, String> entry : DELTAL_REPLACE_MAP.entrySet()) {
                content = content.replace(entry.getKey(), entry.getValue());
            }
        }
        // replace bracket
        content = content.replace("[", "{{");
        content = content.replace("]", "}}");
        return content;
    }
}
