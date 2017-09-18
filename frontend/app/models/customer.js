import Ember from 'ember';
import DS from 'ember-data';
import DependentRelationships from '../mixins/dependent-relationships';

const { attr, hasMany, belongsTo } = DS;

export default DS.Model.extend(DependentRelationships, {
  cpfNumber: attr('string'),
  name: attr('string'),
  email: attr('string'),
  notes: attr('string'),
  birthday: attr('date'),
  createdAt: attr('timestamp'),
  updatedAt: attr('timestamp'),
  address: belongsTo('customer_address', { async: false, cascadeDelete: true }),
  phones: hasMany('customer_phone', { async: false, cascadeDelete: true }),

  isPhonesRemovable: Ember.computed('phones.@each.isDeleted', function() {
    return this.get('phones.[]').filterBy('isDeleted', false).get('length') > 1 ? null : true;
  }),
});
