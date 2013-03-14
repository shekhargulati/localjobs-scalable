package com.localjobs.controllers;

import java.util.Arrays;
import java.util.List;

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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.localjobs.domain.Job;
import com.localjobs.service.CoordinateFinder;

@Controller
@RequestMapping("/api/jobs")
public class JobController {

	@Inject
	MongoTemplate mongoTemplate;
	@Inject
	CoordinateFinder coordinateFinder;

	public JobController() {
	}

	@RequestMapping(value = "/{location}/{skills}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<GeoResult<Job>> allJobsNearToLocationWithSkill(
			@PathVariable("location") String location,
			@PathVariable("skills") String[] skills, Model model)
			throws Exception {
		System.out.println("Searching jobs for location : " + location);
		System.out.println("skills " + Arrays.toString(skills));
		double[] coordinates = coordinateFinder.coordinates(location);
		System.out.println("Found coordinates " + Arrays.toString(coordinates));
		Metric metric = Metrics.KILOMETERS;
		double longitude = coordinates[0];
		double latitude = coordinates[1];
		Query skillsWhereClause = Query.query(Criteria.where("skills").in(skills));
		NearQuery nearQuery = NearQuery
				.near(new Point(longitude, latitude), metric)
				.query(skillsWhereClause)
				.num(10);
		GeoResults<Job> geoResults = mongoTemplate
				.geoNear(nearQuery, Job.class);
		List<GeoResult<Job>> jobs = geoResults.getContent();
		System.out.println("jobs found " + jobs.size());

		for (GeoResult<Job> geoResult : jobs) {
			System.out.println(geoResult.getContent() + " , Distance "
					+ geoResult.getDistance());
		}
		return jobs;
	}

	@RequestMapping
	public String jobsWithDistance(
			@RequestParam("longitude") double longtitude,
			@RequestParam("latitude") double latitude, Model model) {
		Metric metric = Metrics.KILOMETERS;
		NearQuery nearQuery = NearQuery.near(new Point(longtitude, latitude),
				metric).num(100);
		GeoResults<Job> geoResults = mongoTemplate
				.geoNear(nearQuery, Job.class);
		List<GeoResult<Job>> jobs = geoResults.getContent();
		System.out.println("jobs found " + jobs.size());

		for (GeoResult<Job> geoResult : jobs) {
			System.out.println(geoResult.getContent() + " , Distance "
					+ geoResult.getDistance());
		}
		model.addAttribute("jobs", jobs);
		return "geonearjobs";
	}

	@RequestMapping("/spherical")
	public String jobsWithDistanceSpherical(
			@RequestParam("longitude") double longtitude,
			@RequestParam("latitude") double latitude, Model model) {
		Metric metric = Metrics.KILOMETERS;
		NearQuery nearQuery = NearQuery
				.near(new Point(longtitude, latitude), metric).spherical(true)
				.num(100);
		GeoResults<Job> geoResults = mongoTemplate
				.geoNear(nearQuery, Job.class);
		List<GeoResult<Job>> jobs = geoResults.getContent();
		System.out.println("jobs found " + jobs.size());

		for (GeoResult<Job> geoResult : jobs) {
			System.out.println(geoResult.getContent() + " , Distance "
					+ geoResult.getDistance());
		}
		model.addAttribute("jobs", jobs);
		return "geonearjobs";
	}
}
