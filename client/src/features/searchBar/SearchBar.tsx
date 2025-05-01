import { Search } from 'lucide-react';
import './SearchBar.scss';
import 'assets/style/_flex.scss';
import 'assets/style/_typography.scss';

const SearchBar = () => {
	return (
		<div className="searchbar">
			<div className="searchbar-wrapper">
				<div className="searchbar-container">
					<img
						src="/assets/search-icon.png"
						alt="search icon"
						className="searchbar-icon"
					/>
					<input
						type="text"
						placeholder="검색"
						className="searchbar-input body3"
					/>
				</div>
			</div>
		</div>
	);
};

export default SearchBar;
