import Ember from 'ember';
import AuthenticatedRouteMixin from 'ember-simple-auth/mixins/authenticated-route-mixin';

export default Ember.Route.extend(AuthenticatedRouteMixin, {
  model(params) {
    return this.store.findRecord('customer', params.id);
  },
  titleToken: function (model) {
    return model.get('name');
  },
  breadCrumb: {
    title: 'Editar'
  },
});
