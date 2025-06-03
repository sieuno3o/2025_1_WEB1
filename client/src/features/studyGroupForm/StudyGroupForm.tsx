import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import './StudyGroupForm.scss';
import 'assets/style/_flex.scss';
import 'assets/style/_typography.scss';
import {
	createStudyGroup,
	Category,
	Region,
	StudyType,
} from 'api/createGroupFormApi';

interface StudyGroupFormProps {
	onClose: () => void;
}

const MAX_NOTICE_LENGTH = 1000;

const StudyGroupForm: React.FC<StudyGroupFormProps> = ({ onClose }) => {
	const navigate = useNavigate();
	const [groupName, setGroupName] = useState('');
	const [meetingType, setMeetingType] = useState<StudyType | ''>('');
	const [meetingTime, setMeetingTime] = useState('');
	const [meetingCycle, setMeetingCycle] = useState<'월' | '주'>('월');
	const [meetingDay, setMeetingDay] = useState('');
	const [memberCount, setMemberCount] = useState('');
	const [studyTypeDetail, setStudyTypeDetail] = useState('');
	const [notice, setNotice] = useState('');
	const [region, setRegion] = useState<Region | ''>('');
	const [category, setCategory] = useState<Category | ''>('');
	const [startDate, setStartDate] = useState('');

	useEffect(() => {
		if (meetingDay) {
			const dayNum = Number(meetingDay);
			const maxDay = meetingCycle === '주' ? 7 : 31;
			if (dayNum > maxDay) {
				setMeetingDay(String(maxDay));
			}
		}
	}, [meetingCycle]);

	const handleSubmit = async (e: React.FormEvent) => {
		e.preventDefault();

		// 유효성 검사 (제출 시 한 번만 실행됨)
		if (groupName.trim().length < 2) {
			alert('스터디명을 2자 이상 입력해주세요.');
			return;
		}
		if (!meetingType) {
			alert('스터디 형태를 선택해주세요.');
			return;
		}
		if (!meetingTime) {
			alert('모임 시간을 선택해주세요.');
			return;
		}
		if (meetingType === StudyType.오프라인 && !region) {
			alert('지역을 선택해주세요.');
			return;
		}
		const day = Number(meetingDay);
		const maxDay = meetingCycle === '주' ? 7 : 31;
		if (!day || day < 1 || day > maxDay) {
			alert(`올바른 날짜(1~${maxDay})를 입력해주세요.`);
			return;
		}
		const members = Number(memberCount);
		if (!members || members < 3 || members > 12) {
			alert('모집 인원은 3명 이상 12명 이하로 입력해주세요.');
			return;
		}
		if (!category) {
			alert('분야를 선택해주세요.');
			return;
		}
		if (!studyTypeDetail.trim()) {
			alert('세부 분야를 입력해주세요.');
			return;
		}
		if (!startDate) {
			alert('스터디 시작일을 선택해주세요.');
			return;
		}
		const today = new Date().toISOString().slice(0, 10);
		if (startDate < today) {
			alert('스터디 시작일은 오늘 이후 날짜여야 합니다.');
			return;
		}

		// API 요청 바디
		const groupData: any = {
			name: groupName.trim(),
			maxMembers: Number(memberCount),
			notice,
			meetingDays: `${meetingCycle} ${meetingDay}일`,
			meetingTime,
			meetingType,
			category: category as Category,
			type: studyTypeDetail.trim(),
			startDate,
			region: meetingType === StudyType.오프라인 ? (region as Region) : null,
		};

		try {
			const response = await createStudyGroup(groupData);
			alert(response.message);
			onClose(); // 폼 닫기
			navigate('/mypage'); // 마이페이지로 이동
		} catch (error: any) {
			const status = error.response?.status;
			const serverData = error.response?.data;
			const serverMessage =
				typeof serverData === 'string'
					? serverData
					: (serverData?.message ?? '');

			// 중복 그룹명
			if (status === 400 && serverMessage.includes('존재')) {
				alert('이미 존재하는 그룹명입니다.');
				return;
			}
			// 토큰 만료
			if (status === 401 && serverMessage === 'access token expired') {
				alert('로그인이 만료되었습니다. 다시 로그인해주세요.');
				window.location.href = '/login';
				return;
			}

			// 그 외의 에러
			alert('스터디 그룹 생성에 실패했습니다.');
			console.error('스터디 그룹 생성 실패:', error);
		}
	};

	const maxDay = meetingCycle === '주' ? 7 : 31;

	return (
		<form onSubmit={handleSubmit}>
			<div className="study-group-form">
				<div className="title heading2 flex-center">스터디 그룹 생성</div>

				{/* 그룹 명 */}
				<div className="group-name">
					<input
						type="text"
						placeholder="스터디 그룹명 입력"
						value={groupName}
						onChange={(e) => setGroupName(e.target.value)}
						className="group-name-input button2"
					/>
				</div>

				{/* 만남 횟수 & 모집 인원 */}
				<div className="flex-row-between">
					<div>
						<select
							value={meetingCycle}
							onChange={(e) => setMeetingCycle(e.target.value as '월' | '주')}
							className="meeting-period button2"
						>
							<option value="월">월</option>
							<option value="주">주</option>
						</select>
						<input
							type="number"
							placeholder="만남 횟수"
							value={meetingDay}
							onChange={(e) => setMeetingDay(e.target.value)}
							onBlur={() => {
								const day = Number(meetingDay);
								if (day < 1) setMeetingDay('1');
								else if (day > maxDay) setMeetingDay(String(maxDay));
							}}
							className="meeting-period-input button2"
							onWheel={(e) => e.currentTarget.blur()}
							min={1}
							max={maxDay}
							step={1}
						/>
						<span className="button2">일</span>
					</div>
					<div>
						<input
							type="number"
							placeholder="모집 인원"
							value={memberCount}
							onChange={(e) => setMemberCount(e.target.value)}
							onBlur={() => {
								const cnt = Number(memberCount);
								if (cnt < 3) setMemberCount('3');
								else if (cnt > 12) setMemberCount('12');
							}}
							className="member-count-input button2"
							onWheel={(e) => e.currentTarget.blur()}
							min={3}
							max={12}
							step={1}
						/>
						<span className="button2">명</span>
					</div>
				</div>

				{/* 만남 방식 */}
				<div className="meeting-method">
					<button
						type="button"
						onClick={() => setMeetingType(StudyType.온라인)}
						className={`meeting-method-button button2 ${
							meetingType === StudyType.온라인 ? 'bg-green-300' : ''
						}`}
					>
						온라인
					</button>
					<button
						type="button"
						onClick={() => setMeetingType(StudyType.오프라인)}
						className={`meeting-method-button button2 ${
							meetingType === StudyType.오프라인 ? 'bg-green-300' : ''
						}`}
					>
						오프라인
					</button>
				</div>

				{/* 오프라인일 때만 지역 */}
				{meetingType === StudyType.오프라인 && (
					<div>
						<select
							value={region}
							onChange={(e) => setRegion(e.target.value as Region)}
							className="region-select button2"
						>
							<option value="" disabled hidden>
								지역을 선택해주세요
							</option>
							{Object.values(Region)
								.filter((r) => r !== Region.해당없음)
								.map((r) => (
									<option key={r} value={r}>
										{r}
									</option>
								))}
						</select>
					</div>
				)}

				{/* 시간대 */}
				<div className="time-option">
					{['오전', '오후', '저녁', '새벽'].map((time) => (
						<button
							key={time}
							type="button"
							onClick={() => setMeetingTime(time)}
							className={`time-option-button button2 ${
								meetingTime === time ? 'bg-green-300' : ''
							}`}
						>
							{time}
						</button>
					))}
				</div>

				{/* 분야 & 세부 분야 */}
				<div className="flex-row-between">
					<select
						value={category}
						onChange={(e) => setCategory(e.target.value as Category)}
						className="category-select button2"
					>
						<option value="" disabled hidden>
							분야
						</option>
						{Object.values(Category).map((c) => (
							<option key={c} value={c}>
								{c}
							</option>
						))}
					</select>
					<input
						type="text"
						placeholder="세부 분야 입력"
						value={studyTypeDetail}
						onChange={(e) => setStudyTypeDetail(e.target.value)}
						className="study-type-detail-input button2"
					/>
				</div>

				{/* 시작일 */}
				<div className="start-date-section flex-between">
					<label className="button2">스터디는</label>
					<input
						type="date"
						value={startDate}
						onChange={(e) => setStartDate(e.target.value)}
						className="start-date-input button2"
					/>
					<label className="button2">부터 시작해요</label>
				</div>

				{/* 공지사항 */}
				<div>
					<textarea
						placeholder="공지사항 입력"
						value={notice}
						onChange={(e) => setNotice(e.target.value)}
						className="notice button2"
						rows={5}
						maxLength={MAX_NOTICE_LENGTH}
					/>
					<div className="notice-length flex-right button3">
						{notice.length} / {MAX_NOTICE_LENGTH}
					</div>
				</div>

				<button type="submit" className="create-button button2">
					생성하기
				</button>
			</div>
		</form>
	);
};

export default StudyGroupForm;
