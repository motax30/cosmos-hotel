import Ember from 'ember';

export default Ember.Controller.extend({
  session: Ember.inject.service('session'),
  setCurrentUser: Ember.observer('session.data.authenticated.token', function() {
    if(this.get('session.data.authenticated.token')) {
      let claims = JSON.parse(atob(this.get('session.data.authenticated.token').split('.')[1]));
      this.set('session.currentUser', claims);
    }
  }).on('init'),
});
