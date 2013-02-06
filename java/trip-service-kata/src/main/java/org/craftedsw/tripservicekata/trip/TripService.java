package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService {

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
	    User loggedUser = UserSession.getInstance().getLoggedUser();
		return getTripsByUser(user, loggedUser);
	}

    public List<Trip> getTripsByUser(User user, User loggedUser) throws UserNotLoggedInException {
        List<Trip> tripList = new ArrayList<Trip>();
		boolean isFriend = false;
		if (loggedUser != null) {
			for (User friend : user.getFriends()) {
				if (friend.equals(loggedUser)) {
					isFriend = true;
					break;
				}
			}
			if (isFriend) {
				tripList = findTripsByUser(user);
			}
			return tripList;
		} else {
			throw new UserNotLoggedInException();
		}
    }

    protected List<Trip> findTripsByUser(User user) {
        return TripDAO.findTripsByUser(user);
    }
	
}
