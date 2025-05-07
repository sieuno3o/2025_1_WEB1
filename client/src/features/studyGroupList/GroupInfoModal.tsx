import React from 'react';
import './GroupInfoModal.scss';
import 'assets/style/_typography.scss';
import 'assets/style/_flex.scss';

interface Group {
	id: number;
	name: string;
	meetingDays: string;
	meetingTime: string;
	meetingType: string;
	currentMembers: number;
	maxMembers: number;
	region: string;
	category: string;
	type: string;
}

interface Props {
	group: Group;
	onClose: () => void;
	onJoin: () => void;
}

const GroupInfoModal: React.FC<Props> = ({ group, onClose, onJoin }) => {
	return (
		<div className="group-modal-backdrop flex-center" onClick={onClose}>
			<div
				className="group-modal-content flex-col"
				onClick={(e) => e.stopPropagation()}
			>
				<div className="group-name-info body1">{group.name}</div>

				<div>
					<div className="group-modal-value body3">{group.meetingDays}</div>
					<div className="group-modal-value body3">{group.meetingTime}</div>
					<div className="group-modal-value body3">
						{group.region === '해당없음' ? '비대면' : group.region}
					</div>

					<div className="group-modal-value body3">
						{`${group.currentMembers} / ${group.maxMembers}명`}
					</div>

					<div className="group-modal-value body3">
						{group.category} - {group.type}
					</div>
				</div>

				<div className="group-modal-divider" />

				<div className="group-modal-notice body3">📢 공지사항</div>

				<div className="group-modal-actions flex-row-between">
					<div className="group-modal-button join button1" onClick={onJoin}>
						가입하기
					</div>
					<div className="group-modal-button cancel button1" onClick={onClose}>
						취소
					</div>
				</div>
			</div>
		</div>
	);
};

export default GroupInfoModal;
