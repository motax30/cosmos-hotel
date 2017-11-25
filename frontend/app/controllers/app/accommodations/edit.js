import Ember from 'ember';

export default Ember.Controller.extend({
	actions: {
		save(record) {
      const flashMessages = Ember.get(this, 'flashMessages');
			let self = this;
			record.save().then(function() {
        let datetime = new Date().toLocaleString('pt-br');
        flashMessages.success(`Acomodação editada com sucesso em ${datetime}!`);
				self.transitionToRoute('app.accommodations.show', record.id);
			});
		}
	}
});
