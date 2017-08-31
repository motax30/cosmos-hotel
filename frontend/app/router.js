import Ember from 'ember';
import config from './config/environment';

const Router = Ember.Router.extend({
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

});

export default Router;
