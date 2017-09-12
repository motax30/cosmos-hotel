import DS from 'ember-data';

const { attr, belongsTo } = DS;

export default DS.Model.extend({
  street: attr('string'),
  number: attr('string'),
  neighborhood: attr('string'),
  zipCode: attr('string'),
  city: attr('string'),
  state: attr('string'),
  country: attr('string'),
  customer: belongsTo('customer')
});
