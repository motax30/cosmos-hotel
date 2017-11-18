import Ember from 'ember';
import CustomerValidations from '../../validations/customer';
import AddressValidations from '../../validations/address';
import PhoneValidations from '../../validations/phone';
import lookupValidator from 'ember-changeset-validations';
import Changeset from 'ember-changeset';

export default Ember.Component.extend({
  store: Ember.inject.service(),
  toast: Ember.inject.service(),
  init() {
    this._super(...arguments);
    let model = this.get('model');
  },
  actions: {
	  submit: function (accommodation) {
	      this.get('accommodation').validate();   

	      this.$("input[invalid]").first().focus();

	      if (!this.get('receptionist').get('isValid')) {
	        return false;
	      }

	      /* Only in case of has many */
	      this.sendAction('action', accommodation);
	    }
	  }
});
