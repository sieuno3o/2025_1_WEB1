import 'assets/style/_flex.scss';
import 'assets/style/_typography.scss';
import './GroupChecklist.scss';

const GroupChecklist = () => {
	return (
		<div className="group-checklist">
			<div className="check-row">
				<input type="checkbox" className="check-box" />
				<input type="text" className="check-input" />
			</div>
			<div className="check-row">
				<input type="checkbox" className="check-box" />
				<input type="text" className="check-input" />
			</div>
		</div>
	);
};

export default GroupChecklist;
