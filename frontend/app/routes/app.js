import Ember from 'ember';
import AuthenticatedRouteMixin from 'ember-simple-auth/mixins/authenticated-route-mixin';

export default Ember.Route.extend(AuthenticatedRouteMixin, {
	classNames: ["navbar-fixed sidebar-fixed header-fixed fixed-nav footer-fixed"],
	title: function(tokens) {
		return tokens.join(' - ') + ' » Cosmos Star Hotel';
	},
	breadCrumb: {
		title: 'Home',
		path: 'app.dashboard'
	},
	authenticationRoute: "auth"
});
