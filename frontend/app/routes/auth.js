import Ember from 'ember';
import UnauthenticatedRouteMixin from 'ember-simple-auth/mixins/unauthenticated-route-mixin';

export default Ember.Route.extend(UnauthenticatedRouteMixin, {
	title: 'Bem-vindo ao Cosmos Hotel!',
	routeIfAlreadyAuthenticated: 'app.dashboard'
});