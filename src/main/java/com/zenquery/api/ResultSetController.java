package com.zenquery.api;

import au.com.bytecode.opencsv.CSVWriter;
import com.hp.gagawa.java.elements.Strong;
import com.hp.gagawa.java.elements.Table;
import com.hp.gagawa.java.elements.Td;
import com.hp.gagawa.java.elements.Tr;
import com.hp.gagawa.java.elements.Th;
import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.A;
import com.thoughtworks.xstream.XStream;
import com.zenquery.model.DatabaseConnection;
import com.zenquery.model.Query;
import com.zenquery.model.dao.DatabaseConnectionDAO;
import com.zenquery.model.dao.QueryDAO;
import com.zenquery.util.BasicDataSourceFactory;
import com.zenquery.util.MapEntryConverter;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.StringWriter;
import java.util.*;

@Controller
@RequestMapping("/api/v1/resultSetForQuery")
public class ResultSetController {
    private static final Logger logger = Logger.getLogger(ResultSetController.class);

    @Autowired
    private DatabaseConnectionDAO databaseConnectionDAO;

    @Autowired
    private QueryDAO queryDAO;

    @Autowired
    private BasicDataSourceFactory dataSourceFactory;

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = { "application/json; charset=utf-8" })
    public @ResponseBody
    List<Map<String, Object>> currentQuery(
            @PathVariable Integer id
    ) {
        List<Map<String, Object>> rows = getRows(id, null, null);

        return rows;
    }

    @RequestMapping(
            value = "/{id}/{variables}",
            method = RequestMethod.GET,
            produces = { "application/json; charset=utf-8" })
    public @ResponseBody
    List<Map<String, Object>> currentQuery(
            @PathVariable Integer id,
            @PathVariable String variables
    ) {
        List<Map<String, Object>> rows = getRows(id, variables, null);

        return rows;
    }

    @RequestMapping(
            value = "/{id}/size/{size}",
            method = RequestMethod.GET,
            produces = { "application/json; charset=utf-8" })
    public @ResponseBody
    List<Map<String, Object>> currentQuery(
            @PathVariable Integer id,
            @PathVariable Integer size
    ) {
        List<Map<String, Object>> rows = getRows(id, null, size);

        return rows;
    }

    @RequestMapping(
            value = "/{id}/{variables}/size/{size}",
            method = RequestMethod.GET,
            produces = { "application/json; charset=utf-8" })
    public @ResponseBody
    List<Map<String, Object>> currentQuery(
            @PathVariable Integer id,
            @PathVariable String variables,
            @PathVariable Integer size
    ) {
        List<Map<String, Object>> rows = getRows(id, variables, size);

        return rows;
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = { "text/csv; charset=utf-8" })
    public @ResponseBody
    String currentQueryAsCSV(
            @PathVariable Integer id
    ) {
        List<Map<String, Object>> rows = getRows(id, null, null);

        return getCsvResults(rows);
    }

    @RequestMapping(
            value = "/{id}/{variables}",
            method = RequestMethod.GET,
            produces = { "text/csv; charset=utf-8" })
    public @ResponseBody
    String currentQueryAsCSV(
            @PathVariable Integer id,
            @PathVariable String variables
    ) {
        List<Map<String, Object>> rows = getRows(id, variables, null);

        return getCsvResults(rows);
    }

    @RequestMapping(
            value = "/{id}/size/{size}",
            method = RequestMethod.GET,
            produces = { "text/csv; charset=utf-8" })
    public @ResponseBody
    String currentQueryAsCSV(
            @PathVariable Integer id,
            @PathVariable Integer size
    ) {
        List<Map<String, Object>> rows = getRows(id, null, size);

        return getCsvResults(rows);
    }

    @RequestMapping(
            value = "/{id}/{variables}/size/{size}",
            method = RequestMethod.GET,
            produces = { "text/csv; charset=utf-8" })
    public @ResponseBody
    String currentQueryAsCSV(
            @PathVariable Integer id,
            @PathVariable String variables,
            @PathVariable Integer size
    ) {
        List<Map<String, Object>> rows = getRows(id, variables, size);

        return getCsvResults(rows);
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = { "application/xml; charset=utf-8" })
    public @ResponseBody
    String currentQueryAsXML(
            @PathVariable Integer id
    ) {
        List<Map<String, Object>> rows = getRows(id, null, null);

        XStream stream = getXMLStream();

        return stream.toXML(rows.toArray());
    }

    @RequestMapping(
            value = "/{id}/{variables}",
            method = RequestMethod.GET,
            produces = { "application/xml; charset=utf-8" })
    public @ResponseBody
    String currentQueryAsXML(
            @PathVariable Integer id,
            @PathVariable String variables
    ) {
        List<Map<String, Object>> rows = getRows(id, variables, null);

        XStream stream = getXMLStream();

        return stream.toXML(rows.toArray());
    }

    @RequestMapping(
            value = "/{id}/size/{size}",
            method = RequestMethod.GET,
            produces = { "application/xml; charset=utf-8" })
    public @ResponseBody
    String currentQueryAsXML(
            @PathVariable Integer id,
            @PathVariable Integer size
    ) {
        List<Map<String, Object>> rows = getRows(id, null, size);

        XStream stream = getXMLStream();

        return stream.toXML(rows.toArray());
    }

    @RequestMapping(
            value = "/{id}/{variables}/size/{size}",
            method = RequestMethod.GET,
            produces = { "application/xml; charset=utf-8" })
    public @ResponseBody
    String currentQueryAsXML(
            @PathVariable Integer id,
            @PathVariable String variables,
            @PathVariable Integer size
    ) {
        List<Map<String, Object>> rows = getRows(id, variables, size);

        XStream stream = getXMLStream();

        return stream.toXML(rows.toArray());
    }

    @RequestMapping(
            value = "/{mode}/{complete}/{id}",
            method = RequestMethod.GET,
            produces = { "text/html; charset=utf-8" })
    public @ResponseBody
    String currentQueryAsHTML(
            @PathVariable Integer id,
            @PathVariable String mode,
            @PathVariable Boolean complete
    ) {
        List<Map<String, Object>> rows = getRows(id, null, null);

        String html = getHTML(mode, complete, rows);

        return html;
    }

    @RequestMapping(
            value = "/{mode}/{complete}/{id}/{variables}",
            method = RequestMethod.GET,
            produces = { "text/html; charset=utf-8" })
    public @ResponseBody
    String currentQueryAsHTML(
            @PathVariable Integer id,
            @PathVariable String variables,
            @PathVariable String mode,
            @PathVariable Boolean complete
    ) {
        List<Map<String, Object>> rows = getRows(id, variables, null);

        String html = getHTML(mode, complete, rows);

        return html;
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = { "text/html; charset=utf-8" })
    public @ResponseBody
    String currentQueryAsHTML(
            @PathVariable Integer id
    ) {
        List<Map<String, Object>> rows = getRows(id, null, null);

        String html = getHTML("vertical", true, rows);

        return html;
    }

    @RequestMapping(
            value = "/{id}/{variables}",
            method = RequestMethod.GET,
            produces = { "text/html; charset=utf-8" })
    public @ResponseBody
    String currentQueryAsHTML(
            @PathVariable Integer id,
            @PathVariable String variables
    ) {
        List<Map<String, Object>> rows = getRows(id, variables, null);

        String html = getHTML("vertical", true, rows);

        return html;
    }

    @RequestMapping(
            value = "/{mode}/{complete}/{id}/size/{size}",
            method = RequestMethod.GET,
            produces = { "text/html; charset=utf-8" })
    public @ResponseBody
    String currentQueryAsHTML(
            @PathVariable Integer id,
            @PathVariable String mode,
            @PathVariable Boolean complete,
            @PathVariable Integer size
    ) {
        List<Map<String, Object>> rows = getRows(id, null, size);

        String html = getHTML(mode, complete, rows);

        return html;
    }

    @RequestMapping(
            value = "/{mode}/{complete}/{id}/{variables}/size/{size}",
            method = RequestMethod.GET,
            produces = { "text/html; charset=utf-8" })
    public @ResponseBody
    String currentQueryAsHTML(
            @PathVariable Integer id,
            @PathVariable String variables,
            @PathVariable String mode,
            @PathVariable Boolean complete,
            @PathVariable Integer size
    ) {
        List<Map<String, Object>> rows = getRows(id, variables, size);

        String html = getHTML(mode, complete, rows);

        return html;
    }

    @RequestMapping(
            value = "/{id}/size/{size}",
            method = RequestMethod.GET,
            produces = { "text/html; charset=utf-8" })
    public @ResponseBody
    String currentQueryAsHTML(
            @PathVariable Integer id,
            @PathVariable Integer size
    ) {
        List<Map<String, Object>> rows = getRows(id, null, size);

        String html = getHTML("vertical", true, rows);

        return html;
    }

    @RequestMapping(
            value = "/{id}/{variables}/size/{size}",
            method = RequestMethod.GET,
            produces = { "text/html; charset=utf-8" })
    public @ResponseBody
    String currentQueryAsHTML(
            @PathVariable Integer id,
            @PathVariable String variables,
            @PathVariable Integer size
    ) {
        List<Map<String, Object>> rows = getRows(id, variables, size);

        String html = getHTML("vertical", true, rows);

        return html;
    }

    private List<Map<String, Object>> getResultRows(Integer id, String variables) {
        Query query = queryDAO.find(id);
        DatabaseConnection databaseConnection = databaseConnectionDAO.find(query.getDatabaseConnectionId());

        List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
        try {
            BasicDataSource dataSource = dataSourceFactory.getBasicDataSource(
                    databaseConnection.getUrl(),
                    databaseConnection.getUsername(),
                    databaseConnection.getPassword()
            );

            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            if (variables != null) {
                List<Object> arguments = new ArrayList<Object>();
                String[] extractedVariables = variables.split(",");

                for (String variable : extractedVariables) {
                    try {
                        arguments.add(Long.parseLong(variable));
                    } catch(NumberFormatException noLong) {
                        try {
                            arguments.add(Double.parseDouble(variable));
                        } catch(NumberFormatException noDouble) {
                            arguments.add(variable);
                        }
                    }
                }

                rows = jdbcTemplate.queryForList(query.getContent(), arguments.toArray());
            } else {
                rows = jdbcTemplate.queryForList(query.getContent());
            }
        } catch (Exception e) {
            logger.debug(e);
        }

        return rows;
    }

    private String getCsvResults(List<Map<String, Object>> rows) {
        List<String[]> outputRows = new ArrayList<String[]>();
        Boolean first = true;

        StringWriter stringWriter = new StringWriter();
        CSVWriter csvWriter = new CSVWriter(stringWriter);

        for (Map<String, Object> row : rows) {
            if (first) {
                Set<String> keys = row.keySet();
                String[] columnTitles = new String[keys.size()];
                columnTitles = keys.toArray(columnTitles);
                outputRows.add(columnTitles);
                first = false;
            }

            Collection<Object> values = row.values();
            Object[] columnValues = new Object[values.size()];
            columnValues = values.toArray(columnValues);

            Integer numberOfValues = values.size();
            String[] columnOutputValues = new String[numberOfValues];
            for (int i = 0; i < numberOfValues; i++) {
                Object columnValue = columnValues[i];
                columnOutputValues[i] = columnValue.toString();
            }
            outputRows.add(columnOutputValues);
        }

        csvWriter.writeAll(outputRows);

        return stringWriter.toString();
    }

    private XStream getXMLStream() {
        XStream stream = new XStream();
        stream.registerConverter(new MapEntryConverter());
        stream.alias("root", Map.class);
        return stream;
    }

    private String getHTML(String mode, Boolean complete, List<Map<String, Object>> rows) {
        String html;

        if (mode.equals("horizontal")) {
            if (complete) {
                html = getHorizontalResultListHTML(rows);
            } else {
                html = getHorizontalTableHTML(rows);
            }
        } else {
            if (complete) {
                html = getVerticalResultListHTML(rows);
            } else {
                html = getVerticalListHTML(rows);
            }
        }
        return html;
    }

    private String getHorizontalTableHTML(List<Map<String, Object>> rows) {
        Table table = new Table();
        table.setCSSClass("table table-striped table-bordered table-hover");

        Tr tableHeader = new Tr();
        Boolean firstRow = true;

        for (Map<String, Object> row : rows) {
            Tr tableRow = new Tr();

            for (String key : row.keySet()) {
                if (firstRow) {
                    Th th = new Th();
                    th.appendText(key);
                    tableHeader.appendChild(th);
                }

                Td td = new Td();
                tableRow.appendChild(td);

                Object value = row.get(key);
                if (value != null) {
                    appendValueToTd(td, value);
                }
            }

            if (firstRow) {
                table.appendChild(tableHeader);
                firstRow = false;
            }
            table.appendChild(tableRow);
        }

        return table.write();
    }

    private String getVerticalListHTML(List<Map<String, Object>> rows) {
        Div resultSetList = new Div();
        resultSetList.setCSSClass("row");

        for (Map<String, Object> row : rows) {
            Div entry = new Div();
            entry.setCSSClass("col-lg-12");
            resultSetList.appendChild(entry);

            Table entryTable = new Table();
            entryTable.setCSSClass("table table-striped table-bordered table-hover");
            entry.appendChild(entryTable);

            for (String key : row.keySet()) {
                Tr attributeRow = new Tr();

                Td tdKey = new Td();
                Strong strong = new Strong();
                tdKey.appendChild(strong);
                strong.appendText(key);
                attributeRow.appendChild(tdKey);

                Td tdValue = new Td();
                attributeRow.appendChild(tdValue);

                Object value = row.get(key);
                if (value != null) {
                    appendValueToTd(tdValue, value);
                }

                entryTable.appendChild(attributeRow);
            }
        }

        return resultSetList.write();
    }

    private String getHorizontalResultListHTML(List<Map<String, Object>> rows) {
        String html = "<!doctype html><!--[if lt IE 7]>      <html class=\"no-js lt-ie9 lt-ie8 lt-ie7\"> <![endif]--><!--[if IE 7]>         <html class=\"no-js lt-ie9 lt-ie8\"> <![endif]--><!--[if IE 8]>         <html class=\"no-js lt-ie9\"> <![endif]--><!--[if gt IE 8]><!--> <html class=\"no-js\"> <!--<![endif]--><head><meta charset=\"utf-8\"><title>?????? - ZenQuery</title><meta name=\"description\" content=\"\"><meta name=\"viewport\" content=\"width=device-width\"><link rel=\"stylesheet\" href=\"//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css\"><link rel=\"stylesheet\" href=\"//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css\"><script src=\"//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js\"></script></head><body><div class=\"container\">";

        html += getHorizontalTableHTML(rows);

        html += "</div></body></html>";

        return html;
    }

    private String getVerticalResultListHTML(List<Map<String, Object>> rows) {
        String html = "<!doctype html><!--[if lt IE 7]>      <html class=\"no-js lt-ie9 lt-ie8 lt-ie7\"> <![endif]--><!--[if IE 7]>         <html class=\"no-js lt-ie9 lt-ie8\"> <![endif]--><!--[if IE 8]>         <html class=\"no-js lt-ie9\"> <![endif]--><!--[if gt IE 8]><!--> <html class=\"no-js\"> <!--<![endif]--><head><meta charset=\"utf-8\"><title>?????? - ZenQuery</title><meta name=\"description\" content=\"\"><meta name=\"viewport\" content=\"width=device-width\"><link rel=\"stylesheet\" href=\"//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css\"><link rel=\"stylesheet\" href=\"//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css\"><script src=\"//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js\"></script></head><body><div class=\"container\">";

        html += getVerticalListHTML(rows);

        html += "</div></body></html>";

        return html;
    }

    private void appendValueToTd(Td td, Object value) {
        String text = value.toString();

        if (text.startsWith("/api/")) {
            A a = new A();
            a.setHref(text + ".html");
            a.appendText(text);

            text = a.write();
        }

        td.appendText(text);
    }

    private List<Map<String, Object>> getRows(Integer id, String variables, Integer size) {
        List<Map<String, Object>> rows = getResultRows(id, variables);

        if (size != null && size != 0 && size < rows.size()) {
            rows = rows.subList(0, size);
        }

        return rows;
    }
}
