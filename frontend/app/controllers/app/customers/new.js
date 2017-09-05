import Ember from 'ember';

export default Ember.Controller.extend({
	actions: {
		save(record) {
			let self = this;
			record.save().then(function() {
        // record.get('address').filterBy('isNew').invoke('unloadRecord');
				self.transitionToRoute('app.customers.show', record);
			});
		}
	}
});
