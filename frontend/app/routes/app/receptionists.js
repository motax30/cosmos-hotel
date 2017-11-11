import Ember from 'ember';
import AuthenticatedRouteMixin from 'ember-simple-auth/mixins/authenticated-route-mixin';

export default Ember.Route.extend(AuthenticatedRouteMixin, {
	titleToken: "Recepcionistas",
	breadCrumb: {
		title: 'Recepcionistas',
		icon: 'motorcycle'
	}

});
