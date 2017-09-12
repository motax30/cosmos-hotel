import Ember from 'ember';
import CustomerValidations from '../../validations/customer';
import AddressValidations from '../../validations/address';
import PhoneValidations from '../../validations/phone';
import lookupValidator from 'ember-changeset-validations';
import Changeset from 'ember-changeset';

export default Ember.Component.extend({
  store: Ember.inject.service(),

  init() {
    this._super(...arguments);
    let model = get(this, 'model');
    this.customer = new Changeset(model, lookupValidator(CustomerValidations), CustomerValidations);
    this.address = new Changeset(model.get('address'), lookupValidator(AddressValidations), AddressValidations);
    this.phones = [new Changeset(model.get('phones').get('firstObject'), lookupValidator(PhoneValidations), PhoneValidations)];

  },
  actions: {
    addPhone(customer) {
      let store = this.get('store');
      let phone = store.createRecord('customer_phone');

      this.get('phones').pushObject(new Changeset(phone, lookupValidator(PhoneValidations), PhoneValidations));
      customer.get('phones').pushObject(phone);
    },
    submit: function (customer) {
      this.get('customer').validate();
      this.get('address').validate();
      this.get('phones').forEach(phone => {
        phone.validate();
      });

      this.$("input[invalid]").first().focus();

      if (!this.get('customer').get('isValid')) {
        return false;
      }

      if (!this.get('address').get('isValid')) return false;
      this.get('phones').forEach(phone => {
        if (!phone.get('isValid')) {
          return false;
        }
      });

      this.get('customer').execute();
      this.get('address').execute();
      this.get('phones').forEach(phone => {
        phone.execute();
      });

      this.sendAction('action', customer);
    }
  }
});
