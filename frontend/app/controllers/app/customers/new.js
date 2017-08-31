import Ember from 'ember';

export default Ember.Controller.extend({
	actions: {
		save(record) {
			self = this;
			record.save().then(function() {
				self.transitionToRoute('app.customers.show', record);
			});
		}
	}
});