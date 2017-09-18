import Ember from 'ember';
import config from './config/environment';
import RouterScroll from 'ember-router-scroll';

const Router = Ember.Router.extend(RouterScroll, {
	location: config.locationType,
	rootURL: config.rootURL
});

Router.map(function() {
	this.route('auth', { path: '/' });

	this.route('app', { path: '/app' }, function() {
		this.route('dashboard');
		this.route('customers', function() {
			this.route('index', {path: '/'});
			this.route('new');
			this.route('show', {path: ':id'});
			this.route('edit', {path: ':id/edit'});
			this.route('destroy', {path: ':id/destroy'});
		});
	});

	// Not Found Route
  this.route('not-found', { path: '/*path' });
});

export default Router;
