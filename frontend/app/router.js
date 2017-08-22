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
	});
	
});

export default Router;
