import { useNavigate } from 'react-router-dom';
import 'assets/style/_flex.scss';
import 'assets/style/_typography.scss';
import StudyGroupsList from 'features/studyGroupList/StudyGroupsList';
import Header from 'features/header/Header';
import SearchBar from 'features/searchBar/SearchBar';

const MainPage = () => {
	const navigate = useNavigate();
	const isLoggedIn = false;

	const goToLogin = () => {
		navigate('/login');
	};

	return (
		<div className="">
			<div>
				<Header />
				<SearchBar />
				<StudyGroupsList />

				{!isLoggedIn && <button onClick={goToLogin}>로그인</button>}
				{isLoggedIn && <p>환영합니다! 😄</p>}
			</div>
		</div>
	);
};

export default MainPage;
