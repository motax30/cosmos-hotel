import DS from 'ember-data';

const { attr, belongsTo } = DS;

export default DS.Model.extend({
  type: attr('string'),
  number: attr('string'),
  customer: belongsTo('customer')
});
