package com.pipl.api.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AvailableData implements Serializable {
    private static final long serialVersionUID = 1L;
    @Expose
    public FieldCount basic;
    @Expose
    public FieldCount premium;

    /**
     * A summary of the data within an API response
     */
    public static class FieldCount implements Serializable {
        private static final long serialVersionUID = 1L;
        @Expose
        public int names;
        @Expose
        public int addresses;
        @Expose
        public int phones;

        @Expose
        @SerializedName("voip_phones")
        public int voipPhones;

        @Expose
        @SerializedName("mobile_phones")
        public int mobilePhones;
        @Expose
        @SerializedName("landline_phones")
        public int landlinePhones;
        @Expose
        public int emails;

        @Expose
        @SerializedName("personal_emails")
        public int personalEmails;

        @Expose
        @SerializedName("work_emails")
        public int workEmails;

        @Expose
        public int jobs;
        @Expose
        public int educations;
        @Expose
        public int images;
        @Expose
        public int usernames;
        @Expose
        @SerializedName("user_ids")
        public int userIds;
        @Expose
        public int languages;
        @Expose
        public int ethnicities;
        @Expose
        @SerializedName("origin_countries")
        public int originCountries;
        @Expose
        public int urls;
        @Expose
        public int relationships;
        @Expose
        public int dobs;
        @Expose
        public int genders;
        @Expose
        @SerializedName("social_profiles")
        public int socialProfiles;

        @Expose
        public int vehicles;

        public FieldCount() {
        }

        public int getNames() {
            return names;
        }

        public int getAddresses() {
            return addresses;
        }

        public int getPhones() {
            return phones;
        }

        public int getMobilePhones() {
            return mobilePhones;
        }

        public int getLandlinePhones() {
            return landlinePhones;
        }

        public int getVoipPhones() {
            return voipPhones;
        }

        public int getEmails() {
            return emails;
        }

        public int getPersonalEmails() {
            return personalEmails;
        }

        public int getWorkEmails() {
            return workEmails;
        }

        public int getJobs() {
            return jobs;
        }

        public int getEducations() {
            return educations;
        }

        public int getImages() {
            return images;
        }

        public int getUsernames() {
            return usernames;
        }

        public int getUserIds() {
            return userIds;
        }

        public int getLanguages() {
            return languages;
        }

        public int getEthnicities() {
            return ethnicities;
        }

        public int getOriginCountries() {
            return originCountries;
        }

        public int getUrls() {
            return urls;
        }

        public int getRelationships() {
            return relationships;
        }

        public int getDobs() {
            return dobs;
        }

        public int getGenders() {
            return genders;
        }

        /**
         * @return the number of social profile sources
         */
        public int getSocialProfiles() {
            return socialProfiles;
        }

        public int getVehicles() {
            return vehicles;
        }
    }

    public AvailableData() {
    }

    public FieldCount getBasic() {
        return basic;
    }

    public FieldCount getPremium() {
        return premium;
    }
}
