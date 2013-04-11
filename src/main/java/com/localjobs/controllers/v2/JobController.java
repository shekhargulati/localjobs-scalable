package com.localjobs.controllers.v2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoResult;
import org.springframework.data.mongodb.core.geo.GeoResults;
import org.springframework.data.mongodb.core.geo.Metric;
import org.springframework.data.mongodb.core.geo.Metrics;
import org.springframework.data.mongodb.core.geo.Point;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.localjobs.domain.Job;
import com.localjobs.search.FullTextSearchService;

@Controller("jobControllerV2")
@RequestMapping("/api/jobs")
public class JobController {

	@Inject
	private MongoTemplate mongoTemplate;

	@Inject
	private FullTextSearchService fullTextSearchService;

	public JobController() {
	}

	@RequestMapping(value = "/{skills}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<GeoResult<Job>> allJobsNearToLocationWithSkill(
			@PathVariable("skills") String[] skills,
			@RequestParam("longitude") double longitude,
			@RequestParam("latitude") double latitude) throws Exception {
		System.out.println("skills " + Arrays.toString(skills));
		Metric metric = Metrics.KILOMETERS;
		Query skillsWhereClause = Query.query(Criteria.where("skills").in(
				skills));
		NearQuery nearQuery = NearQuery
				.near(new Point(longitude, latitude), metric)
				.query(skillsWhereClause).num(10);
		GeoResults<Job> geoResults = mongoTemplate
				.geoNear(nearQuery, Job.class);
		List<GeoResult<Job>> jobs = geoResults.getContent();
		System.out.println("jobs found " + jobs.size());
		return jobs;
	}

	@RequestMapping(value = "/fulltext/{query}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Job> fullTextSearch(@PathVariable("query") String query) {
		System.out.println("Full text search query is .. " + query);
		Set<String> documentIds = fullTextSearchService.search(query);
		if (CollectionUtils.isEmpty(documentIds)) {
			return new ArrayList<Job>();
		}
		Query idsQuery = Query.query(Criteria.where("_id").in(documentIds));
		return mongoTemplate.find(idsQuery, Job.class);
	}

}
