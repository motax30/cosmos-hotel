import Ember from 'ember';
import ApplicationRouteMixin from 'ember-simple-auth/mixins/application-route-mixin';

export default Ember.Route.extend(ApplicationRouteMixin, {
	session: Ember.inject.service('session'),
	authenticationRoute: "auth",
	routeAfterAuthentication: "app.dashboard",
	routeIfAlreadyAuthenticated: "app.dashboard",
	actions: {
		logout: function() {
			this.get('session').invalidate();
		}
	}
});