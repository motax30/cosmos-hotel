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
    this.customer = new Changeset(model, lookupValidator(CustomerValidations), CustomerValidations);
    this.address = new Changeset(model.get('address'), lookupValidator(AddressValidations), AddressValidations);
    this.phones = [];
    model.get('phones').forEach(phone => {
        this.phones.push(new Changeset(phone, lookupValidator(PhoneValidations), PhoneValidations))
    });
  },
  actions: {
    addPhone(customer) {
      let store = this.get('store');
      let phone = store.createRecord('customer_phone');

      this.get('phones').pushObject(new Changeset(phone, lookupValidator(PhoneValidations), PhoneValidations));
      customer.get('phones').pushObject(phone);
    },
    removePhone(customer, phone) {
      let phoneModel = phone.get('_content').get('_internalModel');
      customer.get('phones').removeObject(phoneModel);
      phoneModel.deleteRecord();
    },
    fillAddressByZipCode(address) {
      let toast = this.get('toast');
      let zipCode = address.get('zipCode');
      address.validate('zipCode');
      let isValid = (address.get('errors').findBy('key', 'zipCode')) === undefined;

      if (isValid) {
        Ember.$.getJSON("http://api.postmon.com.br/v1/cep/" + zipCode).done(function(data) {
          address.set('street', data.logradouro);
          address.set('neighborhood', data.bairro);
          address.set('city', data.cidade);
          address.set('state', data.estado);
          toast.success(`Endereço do CEP ${zipCode} preenchido!`);
        }).fail(function(response) {
          if (response.status === 404) {
            toast.warning(`CEP ${zipCode} não encontrado.`);
          }
        });
      }
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

      /* Only in case of has many */
      let phoneValidation = true;
      if (!this.get('address').get('isValid')) return false;
      this.get('phones').filterBy('isDeleted', false).forEach(phone => {
        if (!phone.get('isValid')) {
          phoneValidation = false;
        }
      });
      if (!phoneValidation) return false;

      this.get('customer').execute();
      this.get('address').execute();
      this.get('phones').filterBy('isDeleted', false).forEach(phone => {
        phone.execute();
      });

      this.sendAction('action', customer);
    }
  }
});
