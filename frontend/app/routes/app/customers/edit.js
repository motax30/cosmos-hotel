import Ember from 'ember';
import AuthenticatedRouteMixin from 'ember-simple-auth/mixins/authenticated-route-mixin';

export default Ember.Route.extend(AuthenticatedRouteMixin, {
  model(params) {
    return this.store.find('customer', params.id);
  },
  actions: {
    willTransition() {
      const record = this.controller.get('model');
      return record.rollbackAttributes();
    }
  }
});
