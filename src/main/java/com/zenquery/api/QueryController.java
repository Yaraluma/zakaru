package com.zenquery.api;

import com.zenquery.model.Query;
import com.zenquery.model.dao.QueryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/queries")
public class QueryController {
    @Autowired
    private QueryDAO queryDAO;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { "application/xml", "application/json" })
	public @ResponseBody
    Query find(@PathVariable Integer id) {
		return queryDAO.find(id);
	}

    @RequestMapping(value = "/findByDatabaseConnectionId/{id}", method = RequestMethod.GET, produces = { "application/xml", "application/json" })
    public @ResponseBody
    List<Query> findByDatabaseConnectionId(@PathVariable Integer id) {
        return queryDAO.findByDatabaseConnectionId(id);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = { "application/xml", "application/json" },
            produces = { "application/xml", "application/json" })
    public @ResponseBody
    Query create(
            @RequestBody Query query
    ) {
        Number id = queryDAO.insert(query);

        return queryDAO.find(new Long(id.longValue()).intValue());
    }

    @RequestMapping(
            value = "/{id}",
            consumes = { "application/xml", "application/json" },
            method = RequestMethod.PUT)
    public @ResponseBody String update(
            @PathVariable Integer id,
            @RequestBody Query query
    ) {
        queryDAO.update(id, query);

        return "OK";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String delete(@PathVariable Integer id) {
        queryDAO.delete(id);

        return "OK";
    }
}