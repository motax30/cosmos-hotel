import Ember from 'ember';
import DS from 'ember-data';
import DependentRelationships from '../mixins/dependent-relationships';

const { attr, hasMany, belongsTo } = DS;

export default DS.Model.extend(DependentRelationships, {
	typeAccommodation: attr('string'),
	dailyPrice: attr('double'),
	numberofbeds: attr('integer'),
});