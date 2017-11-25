import Ember from 'ember';
import AccommodationValidations from '../../validations/accommodation';
import lookupValidator from 'ember-changeset-validations';
import Changeset from 'ember-changeset';

export default Ember.Component.extend({
  store: Ember.inject.service(),
  toast: Ember.inject.service(),
  init() {
    this._super(...arguments);
    let model = this.get('model');
    this.accommodation = new Changeset(model, lookupValidator(AccommodationValidations), AccommodationValidations);
  },
  actions: {
	  submit: function (accommodation) {
	      this.get('accommodation').validate();

	      this.$("input[invalid]").first().focus();

	      if (!this.get('accommodation').get('isValid')) {
	        return false;
	      }

        this.get('accommodation').execute();
	      /* Only in case of has many */
	      this.sendAction('action', accommodation);
	    }
	  }
});
